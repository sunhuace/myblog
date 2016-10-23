package com.myblog.serviveImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myblog.dao.FRIENDLINKMapper;
import com.myblog.entity.FRIENDLINK;
import com.myblog.service.FriendLinkService;

@Service("friendLinkService")
public class FriendLinkServiceImpl implements FriendLinkService {
	
	@Autowired
	private FRIENDLINKMapper friendlinkMapper;

	public List<FRIENDLINK> findAllLink() {
		return friendlinkMapper.findAllLink();
	}

}
