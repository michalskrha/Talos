package sk.talos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import sk.talos.domain.post.PostDto;
import sk.talos.model.Post;
import sk.talos.model.common.Auditable;
import sk.talos.model.common.BaseEntity;

import java.util.List;

@Mapper
public abstract class PostMapper {

    public static PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);


    @Mappings({
            @Mapping(target = BaseEntity.Fields.id, ignore = true),
            @Mapping(target = Auditable.Fields.createdDateTime, ignore = true),
            @Mapping(target = Auditable.Fields.lastModifiedDateTime, ignore = true),
            @Mapping(target = Auditable.Fields.createdBy, ignore = true),
            @Mapping(target = Auditable.Fields.lastModifiedBy, ignore = true)
    })
    public abstract Post postDtoToPost(PostDto postDto);

    public abstract PostDto postToPostDto(Post post);

    public abstract List<PostDto> postsToPostDtoList(List<Post> posts);

    @Mappings({
            @Mapping(target = BaseEntity.Fields.id, ignore = true),
            @Mapping(target = Auditable.Fields.createdDateTime, ignore = true),
            @Mapping(target = Auditable.Fields.lastModifiedDateTime, ignore = true),
            @Mapping(target = Auditable.Fields.createdBy, ignore = true),
            @Mapping(target = Auditable.Fields.lastModifiedBy, ignore = true),
            @Mapping(target = Post.Fields.userId, ignore = true)
    })
    public abstract void updatePostFromPostDto(@MappingTarget Post post, PostDto postDto);

}
