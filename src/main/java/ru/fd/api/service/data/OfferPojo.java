/*
 * Copyright 2020 ViiSE
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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@ApiModel(value = "Offer", description = "Акция")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class OfferPojo {

    @ApiModelProperty(notes = "ID акции", position = 1)
    private long id;
    @ApiModelProperty(notes = "Название акции", position = 2)
    private String name;
    @ApiModelProperty(notes = "Дата акции", position = 3)
    private DateOfferPojo dateOffer;
    @ApiModelProperty(notes = "Подразделения акции", position = 4)
    private List<DepartmentPojo> departments;

    @JsonIgnore
    private String rawListDepartmentsId;

    public String getRawListDepartmentsId() {
        return rawListDepartmentsId;
    }

    public void setRawListDepartmentsId(String rawListDepartmentsId) {
        this.rawListDepartmentsId = rawListDepartmentsId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateOffer(DateOfferPojo dateOffer) {
        this.dateOffer = dateOffer;
    }

    public void setDepartments(List<DepartmentPojo> departments) {
        this.departments = departments;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public DateOfferPojo getDateOffer() {
        return dateOffer;
    }

    public List<DepartmentPojo> getDepartments() {
        return departments;
    }
}
