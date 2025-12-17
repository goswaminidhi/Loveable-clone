package com.loveableclone.mapper;


import com.loveableclone.dto.member.MemberResponse;
import com.loveableclone.entity.ProjectMember;
import com.loveableclone.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface ProjectMemberMapper {

    @Mapping(target = "userId",source = "id")
    @Mapping(target = "projectRole",constant = "OWNER")
    MemberResponse toProjectMemberResponseFromOwner(User user);


    @Mapping(target = "userId",source = "user.id")
    @Mapping(target = "name",source = "user.name")
    MemberResponse toProjectMemberResponseFromMember(ProjectMember projectMember);

}
