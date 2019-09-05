package com.github.mrcaoyc.office.online.factory.model.dto;

import com.github.mrcaoyc.office.online.factory.constants.ActionEnum;
import lombok.Data;

/**
 * @author CaoYongCheng
 */
@Data
public class DiscoveryActionDTO {
    private ActionEnum action;
    private String fileUrl;
}
