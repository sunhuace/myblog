package com.myblog.lucene;

import java.io.StringReader;
import java.nio.file.Paths;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.myblog.entity.BLOG;
import com.myblog.utils.DateUtil;
import com.myblog.utils.StringUtil;

/**
 * 博客索引类
 * 
 * @author Administrator
 *
 */
public class BlogIndex {

	private Directory dir;

	/**
	 * 获取IndexWriter实例
	 * 
	 * @return
	 * @throws Exception
	 */
	private IndexWriter getWriter() throws Exception {
		dir = FSDirectory.open(Paths.get("C://lucene"));
		SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
		IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
		IndexWriter writer = new IndexWriter(dir, iwc);
		return writer;
	}

	/**
	 * 添加博客索引
	 * 
	 * @param BLOG
	 * @throws Exception
	 */
	public void addIndex(BLOG BLOG) throws Exception {
		IndexWriter writer = getWriter();
		Document doc = new Document();
		doc.add(new StringField("id", String.valueOf(BLOG.getId()), Field.Store.YES));
		doc.add(new TextField("title", BLOG.getTitle(), Field.Store.YES));
		doc.add(new StringField("releasedate", DateUtil.formatDate(new Date(), "yyyy-MM-dd"), Field.Store.YES));
		doc.add(new TextField("content", BLOG.getContentNoTag(), Field.Store.YES));
		writer.addDocument(doc);
		writer.close();
	}

	/**
	 * 删除指定博客的索引
	 * 
	 * @param blogId
	 * @throws Exception
	 */
	public void deleteIndex(String blogId) throws Exception {
		IndexWriter writer = getWriter();
		writer.deleteDocuments(new Term("id", blogId));
		writer.forceMergeDeletes(); // 强制删除
		writer.commit();
		writer.close();
	}

	/**
	 * 更新博客索引
	 * 
	 * @param BLOG
	 * @throws Exception
	 */
	public void updateIndex(BLOG BLOG) throws Exception {
		IndexWriter writer = getWriter();
		Document doc = new Document();
		doc.add(new StringField("id", String.valueOf(BLOG.getId()), Field.Store.YES));// 不会产生分词
		doc.add(new TextField("title", BLOG.getTitle(), Field.Store.YES));
		doc.add(new StringField("releasedate", DateUtil.formatDate(new Date(), "yyyy-MM-dd"), Field.Store.YES));
		doc.add(new TextField("content", BLOG.getContentNoTag(), Field.Store.YES));
		writer.updateDocument(new Term("id", String.valueOf(BLOG.getId())), doc);
		writer.close();
	}

	/**
	 * 查询博客信息 返回一个List
	 * 
	 * @param q
	 * @return
	 * @throws Exception
	 */
	public List<BLOG> searchBlog(String q) throws Exception {
		dir = FSDirectory.open(Paths.get("C://lucene"));
		IndexReader reader = DirectoryReader.open(dir);
		IndexSearcher is = new IndexSearcher(reader);
		BooleanQuery.Builder booleanQuery = new BooleanQuery.Builder();
		SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
		QueryParser parser = new QueryParser("title", analyzer);
		Query query = parser.parse(q);

		QueryParser parser2 = new QueryParser("content", analyzer);
		Query query2 = parser2.parse(q);

		booleanQuery.add(query, BooleanClause.Occur.SHOULD);
		booleanQuery.add(query2, BooleanClause.Occur.SHOULD);

		TopDocs hits = is.search(booleanQuery.build(), 100);
		QueryScorer scorer = new QueryScorer(query);
		Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);
		SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<b><font color='red'>", "</font></b>");
		Highlighter highlighter = new Highlighter(simpleHTMLFormatter, scorer);
		highlighter.setTextFragmenter(fragmenter);

		List<BLOG> blogList = new LinkedList<BLOG>();
		for (ScoreDoc scoreDoc : hits.scoreDocs) {
			Document doc = is.doc(scoreDoc.doc);
			BLOG BLOG = new BLOG();
			BLOG.setId(Integer.parseInt(doc.get("id")));
			BLOG.setReleaseDateStr(doc.get("releasedate"));
			String title = doc.get("title");
			String content = StringEscapeUtils.escapeHtml(doc.get("content"));
			if (title != null) {
				TokenStream tokenStream = analyzer.tokenStream("title", new StringReader(title));
				String hTitle = highlighter.getBestFragment(tokenStream, title);
				if (StringUtil.isEmpty(hTitle)) {
					BLOG.setTitle(title);
				} else {
					BLOG.setTitle(hTitle);
				}
			}

			if (content != null) {
				TokenStream tokenStream = analyzer.tokenStream("content", new StringReader(content));
				String hContent = highlighter.getBestFragment(tokenStream, content);
				if (StringUtil.isEmpty(hContent)) {
					if (content.length() <= 200) {
						BLOG.setContent(content);
					} else {
						BLOG.setContent(content.substring(0, 200));
					}
				} else {
					BLOG.setContent(hContent);
				}
			}
			blogList.add(BLOG);
		}
		return blogList;
	}
	
	public static void main(String[] args) throws Exception {
		List<BLOG> searchBlog = new BlogIndex().searchBlog("RRR");
		System.out.println(searchBlog);
	}

}
