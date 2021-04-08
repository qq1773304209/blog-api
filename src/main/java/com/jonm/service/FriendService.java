package com.jonm.service;

import com.jonm.entity.Friend;
import com.jonm.model.vo.FriendInfo;

import java.util.List;

public interface FriendService {
	List<Friend> getFriendList();

	List<com.jonm.model.vo.Friend> getFriendVOList();

	void updateFriendPublishedById(Long friendId, Boolean published);

	void saveFriend(Friend friend);

	void updateFriend(com.jonm.model.dto.Friend friend);

	void deleteFriend(Long id);

	void updateViewsByNickname(String nickname);

	FriendInfo getFriendInfo(boolean cache, boolean md);

	void updateFriendInfoContent(String content);

	void updateFriendInfoCommentEnabled(Boolean commentEnabled);
}
