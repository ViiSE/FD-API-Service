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

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

@ApiModel(value = "DateOffer", description = "Дата акции")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DateOfferPojo {

    @ApiModelProperty(example = "2020-01-13 00:45:36", notes = "Дата и время начала акции (yyyy-MM-dd HH:mm:ss)", position = 1)
    private LocalDateTime startsAt;
    @ApiModelProperty(example = "2020-01-13 00:45:36", notes = "Дата и время окончания акции (yyyy-MM-dd HH:mm:ss)", position = 2)
    private LocalDateTime finishesAt;

    public void setStartsAt(LocalDateTime startsAt) {
        this.startsAt = startsAt;
    }

    public void setFinishesAt(LocalDateTime finishesAt) {
        this.finishesAt = finishesAt;
    }

    public LocalDateTime getStartsAt() {
        return startsAt;
    }

    public LocalDateTime getFinishesAt() {
        return finishesAt;
    }
}
