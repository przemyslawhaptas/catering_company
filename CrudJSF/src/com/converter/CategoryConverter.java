package com.converter;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.facade.CategoryFacade;
import com.model.Category;

@ManagedBean
@RequestScoped
public class CategoryConverter implements Converter {
	
    @EJB
    private CategoryFacade categoryFacade;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String id) {
		if (id == null || id.isEmpty()) {
			return null;
		}
		
		Category result = categoryFacade.find(Integer.parseInt(id));
		if (result == null) {
			try {
				throw new Exception("dupa");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
//		System.out.println("categoryFacade: " + categoryFacade);
//		System.out.println("Integer.parseInt(id): " + Integer.parseInt(id));
//		System.out.println("result: " + result);
		
		return result;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object category) {
		if (category == null) {
			return "";
		}
		
		int id = ((Category) category).getId();
		
		return String.valueOf(id);
	}
}