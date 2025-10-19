package com.hxl.springmvc.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 视图模型对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModelAndView {

    private Object view;

    private ModelMap modelMap;
}
