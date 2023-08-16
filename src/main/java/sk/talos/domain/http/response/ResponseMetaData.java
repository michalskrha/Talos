package sk.talos.domain.http.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ResponseMetaData {

    @ApiModelProperty(notes = "Number of current page.", example = "0", position = 1)
    private Integer currentPage;
    @ApiModelProperty(notes = "Number of first item in returned page items.", example = "0", position = 2)
    private Integer from;
    @ApiModelProperty(notes = "Last available page of items.", example = "7", position = 3)
    private Integer lastPage;
    @ApiModelProperty(notes = "Count of items on single page.", example = "15", position = 4)
    private Integer perPage;
    @ApiModelProperty(notes = "API path of current page.", example = "/api/office/trips", position = 5)
    private String path;
    @ApiModelProperty(notes = "Number of last item in returned page items.", example = "15", position = 6)
    private Integer to;
    @ApiModelProperty(notes = "Total count of items.", example = "70", position = 7)
    private Long total;
    @ApiModelProperty(notes = "List of protected attributes to edit of model.", example = "70", position = 8)
    private Set<String> protectedAttributes = new HashSet<>();
    @ApiModelProperty(notes = "List of tenant system permissions.", example = "70", position = 9)
    private Set<String> systemPermissions = new HashSet<>();

    private ResponseMetaData() {
        // Private constructor to enforce usage of the builder
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private ResponseMetaData metaData = new ResponseMetaData();

        public Builder currentPage(Integer currentPage) {
            metaData.setCurrentPage(currentPage);
            return this;
        }

        public Builder from(Integer from) {
            metaData.setFrom(from);
            return this;
        }

        public Builder lastPage(Integer lastPage) {
            metaData.setLastPage(lastPage);
            return this;
        }

        public Builder perPage(Integer perPage) {
            metaData.setPerPage(perPage);
            return this;
        }

        public Builder path(String path) {
            metaData.setPath(path);
            return this;
        }

        public Builder to(Integer to) {
            metaData.setTo(to);
            return this;
        }

        public Builder total(Long total) {
            metaData.setTotal(total);
            return this;
        }

        public Builder protectedAttributes(Set<String> protectedAttributes) {
            metaData.setProtectedAttributes(protectedAttributes);
            return this;
        }

        public Builder systemPermissions(Set<String> systemPermissions) {
            metaData.setSystemPermissions(systemPermissions);
            return this;
        }

        public ResponseMetaData build() {
            return metaData;
        }
    }
}
