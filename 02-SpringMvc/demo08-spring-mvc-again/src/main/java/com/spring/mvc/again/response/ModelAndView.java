package com.spring.mvc.again.response;

import com.spring.mvc.again.view.ModelMap;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ModelAndView implements View{

    /**
     * View
     */
    private Object view;

    /**
     * Model
     */
    private ModelMap model;

    public void setViewName(String viewName) {
        setView(viewName);
    }
}
