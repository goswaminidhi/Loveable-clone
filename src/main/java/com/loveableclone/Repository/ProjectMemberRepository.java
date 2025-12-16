package com.loveableclone.Repository;

import com.loveableclone.entity.ProjectMember;
import com.loveableclone.entity.ProjectMemberId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMemberId> {
    List<ProjectMember> findByIdProjectId(Long projectId);//here we are find the members of a specific project.So no
    // need for the userId.
}
