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
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Vendor", description = "Производитель")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class VendorPojo {

    @ApiModelProperty(notes = "GID производителя", position = 1)
    private final String id;
    @ApiModelProperty(notes = "Имя производителя", position = 2)
    private final String name;
    @ApiModelProperty(notes = "Код Avarda", position = 3)
    private String codeAvarda = "";

    @JsonCreator
    public VendorPojo(
            @JsonProperty("id") String id,
            @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @JsonProperty("code_avarda")
    public void setCodeAvarda(String codeAvarda) {
        this.codeAvarda = codeAvarda;
    }

    @JsonProperty("code_avarda")
    public String getCodeAvarda() {
        return codeAvarda;
    }
}
