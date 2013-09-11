package com.jaus.services;

import java.util.HashSet;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jaus.persistence.Prueba;
import com.jaus.persistence.PruebaDao;
import com.vividsolutions.jts.geom.Coordinate;

@Controller
public class JausService {
	
	@Autowired
	private PruebaDao pruebaDao;
	
	@RequestMapping(value="/jaus/{lat}/{lon}", method=RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody String getJaus(@PathVariable String lat, @PathVariable String lon){
		HashSet<Prueba> pruebas = (HashSet<Prueba>)pruebaDao.getAll();
		StringBuilder builder = new StringBuilder();
		Coordinate[] coords = new Coordinate[pruebas.size()];
		Iterator<Prueba> p = pruebas.iterator();
		for (int i=0;i<pruebas.size();i++) {
			Prueba prueba = p.next();
			builder.append("[id:").append(prueba.getId()).append(", X:").append(prueba.getWay().getX()).append(", Y:")
			.append(prueba.getWay().getY()).append(", Coordinates:").append(prueba.getWay().getCoordinate().x).append("|")
			.append(prueba.getWay().getCoordinate().y).append("|").append(prueba.getWay().getCoordinate().z).append("]\n");
			coords[i]=prueba.getWay().getCoordinate();
		}
		builder.append("Distance A-B:").append(coords[0].distance(coords[1])).append("\n");
		builder.append("Distance A-C:").append(coords[0].distance(coords[2])).append("\n");
		builder.append("Distance B-C:").append(coords[1].distance(coords[1])).append("\n");
		return builder.toString();
	}

	public PruebaDao getPruebaDao() {
		return pruebaDao;
	}

	public void setPruebaDao(PruebaDao pruebaDao) {
		this.pruebaDao = pruebaDao;
	}
}
