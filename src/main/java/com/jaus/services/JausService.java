package com.jaus.services;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jaus.persistence.PruebaDao;

@Controller
public class JausService {

	@Autowired
	private PruebaDao pruebaDao;

	@RequestMapping(value = "/jaus/{lat}/{lon}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody
	String getJaus(@PathVariable String lat, @PathVariable String lon) {
		// HashSet<Prueba> pruebas = (HashSet<Prueba>)pruebaDao.getAll();
		// StringBuilder builder = new StringBuilder();
		// Coordinate[] coords = new Coordinate[pruebas.size()];
		// Iterator<Prueba> p = pruebas.iterator();
		// for (int i=0;i<pruebas.size();i++) {
		// Prueba prueba = p.next();
		// builder.append("[id:").append(prueba.getId()).append(", X:").append(prueba.getWay().getX()).append(", Y:")
		// .append(prueba.getWay().getY()).append(", Coordinates:").append(prueba.getWay().getCoordinate().x).append("|")
		// .append(prueba.getWay().getCoordinate().y).append("|").append(prueba.getWay().getCoordinate().z).append("]\n");
		// coords[i]=prueba.getWay().getCoordinate();
		// }
		// builder.append("Distance A-B:").append(coords[0].distance(coords[1])).append("\n");
		// builder.append("Distance A-C:").append(coords[0].distance(coords[2])).append("\n");
		// builder.append("Distance B-C:").append(coords[1].distance(coords[1])).append("\n");
		//
		// return builder.toString();
		return pruebaDao.getAsGeoJson(5);
	}

	@RequestMapping(value = "/geojson/{lat}/{lon}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody
	String getGeoJson(@PathVariable String lat, @PathVariable String lon) {
		StringBuilder geoJson = new StringBuilder(
				"{\"type\": \"FeatureCollection\",\"features\": [");
		List<String> features = pruebaDao.getAsGeoJson();
		for (Iterator iterator = features.iterator(); iterator.hasNext();) {
			String feature = (String) iterator.next();
			geoJson.append("{\"type\": \"Feature\",\"geometry\":")
					.append(feature).append("}");
			if(iterator.hasNext()){
				geoJson.append(",");
			}
		}
		return geoJson.append("]}").toString();
	}

	public PruebaDao getPruebaDao() {
		return pruebaDao;
	}

	public void setPruebaDao(PruebaDao pruebaDao) {
		this.pruebaDao = pruebaDao;
	}
}
