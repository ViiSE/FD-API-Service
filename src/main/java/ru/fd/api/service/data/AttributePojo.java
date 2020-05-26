/*
 * Copyright 2019 ViiSE
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ru.fd.api.service.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Attribute", description = "Атрибут товара")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class AttributePojo {

    @ApiModelProperty(notes = "GID атрибута", position = 1)
    private String id;
    @ApiModelProperty(notes = "ID группы атрибута", position = 2)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long groupId;
    @ApiModelProperty(notes = "Название атрибута", position = 3)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    @ApiModelProperty(notes = "Значение атрибута", position = 4)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String value;
    @ApiModelProperty(notes = "Код аварда", position = 5)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String codeAvarda;

    public String getId() {
        return id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setCodeAvarda(String codeAvarda) {
        this.codeAvarda = codeAvarda;
    }

    public String getCodeAvarda() {
        return codeAvarda;
    }
}
