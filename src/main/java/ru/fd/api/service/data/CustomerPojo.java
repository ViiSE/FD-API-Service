/*
 *  Copyright 2019 ViiSE.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package ru.fd.api.service.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Customer", description = "Покупатель, оформивший заказ")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerPojo {

    @ApiModelProperty(notes = "Номер покупателя", position = 1)
    private String phoneNumber;
    @ApiModelProperty(notes = "Email покупателя", position = 2)
    private String email;
    @ApiModelProperty(notes = "Имя покупателя (Имя, Имя Фамилия или ФИО)", position = 3)
    private String name;
    @ApiModelProperty(notes = "<i>Для юридического лица</i>: ИНН покупателя", position = 4)
    private String inn;
    @ApiModelProperty(notes = "<i>Для юридического лица</i>: КПП покупателя", position = 5)
    private String kpp;
    @ApiModelProperty(notes = "Тип покупателя", position = 6)
    private short type;

    public String getPhoneNumber() {
        return phoneNumber == null ? "" : phoneNumber;
    }

    public String getEmail() {
        return email == null ? "" : email;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public String getInn() {
        return inn == null ? "" : inn;
    }

    public String getKpp() {
        return kpp == null ? "" : kpp;
    }

    public short getType() {
        return type;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public void setType(short type) {
        this.type = type;
    }
}
