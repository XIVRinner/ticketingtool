package com.pmark.ticketingtool.model.repositories;

import com.pmark.ticketingtool.model.entity.Group;
import com.pmark.ticketingtool.model.entity.GroupMember;
import com.pmark.ticketingtool.model.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroupMemberRepository extends CrudRepository<GroupMember, Integer> {

    List<GroupMember> findAllByUser(User u);

}
