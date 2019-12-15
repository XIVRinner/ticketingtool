package com.pmark.ticketingtool.model.entity;

import com.pmark.ticketingtool.model.abstractmodel.JSONBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="GROUP_MEMBERS")
@NamedQuery(name="GroupMember.findAll", query="SELECT c FROM GroupMember c")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupMember extends JSONBuilder {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "user_id")
    @OneToOne(fetch = FetchType.EAGER)
    private User user;

    @JoinColumn(name = "group_id")
    @OneToOne(fetch = FetchType.EAGER)
    private Group group;
}
