package com.yuheng.pangolin.model.bbs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BBSHomeModel {
    private List<BBSPostRes> posts;
}
