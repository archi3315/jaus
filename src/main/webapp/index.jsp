<!DOCTYPE html>

<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> --%>
    
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
<%-- <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> --%>

<html>
	<head>
		<script src="http://openlayers.org/api/OpenLayers.js"></script>
		<meta charset="utf-8">
		<title>Welcome</title>
		<link rel="stylesheet" href="style.css" type="text/css" />
		<script type="text/javascript">
		var map;
		var vector;
		function init(){
			var lat=-34.90567;
	        var lon=-56.14819;
	        var zoom=16;
// 			vector = new OpenLayers.Layer.Vector("GeoJSON", {
// 			    projection: "EPSG:4326",
// 			    strategies: [new OpenLayers.Strategy.Fixed()],
// 			    styleMap: new OpenLayers.StyleMap({
//                     pointRadius: "5",
//                     fillColor: "#666666"
//                 }),
// 			    protocol: new OpenLayers.Protocol.HTTP({
// 			        url: "http://localhost:9999/web/geojson/1/1",
// 			        format: new OpenLayers.Format.GeoJSON()
// 			    })
// 			});

// 			var uruguayLayer = new OpenLayers.Layer.OSM("Uruguay Tiles", "http://192.168.33.10/uruguay/${z}/${x}/${y}.png", {numZoomLevels: 22});
// 			var center = new OpenLayers.LonLat(-56.14819,-34.90567).transform("EPSG:4326", "EPSG:900913");
			
// 			map = new OpenLayers.Map({
// 			    div: "map",
// 			    layers: [vector],
// 			    maxExtent: new OpenLayers.Bounds(-58.1754,-35.057,-53.3249,-31.5083),
//                 maxResolution: 156543.0339,
//                 units: 'm',
//                 projection: new OpenLayers.Projection("EPSG:900913"),
//                 displayProjection: new OpenLayers.Projection("EPSG:4326"),
//                 controls:[
//                           new OpenLayers.Control.Navigation(),
//                           new OpenLayers.Control.PanZoomBar(),
//                           new OpenLayers.Control.Permalink(),
//                           new OpenLayers.Control.ScaleLine({geodesic: true}),
//                           new OpenLayers.Control.Permalink('permalink'),
//                           new OpenLayers.Control.MousePosition(),
//                           new OpenLayers.Control.Attribution()],
// 			    numZoomLevels: 22
// 			});
			map = new OpenLayers.Map ("map", {
					controls:[
	                    new OpenLayers.Control.Navigation(),
	                    new OpenLayers.Control.PanZoomBar(),
	                    new OpenLayers.Control.Permalink(),
	                    new OpenLayers.Control.ScaleLine({geodesic: true}),
	                    new OpenLayers.Control.Permalink('permalink'),
	                    new OpenLayers.Control.MousePosition(),
	                    new OpenLayers.Control.Attribution()],
	                maxExtent: new OpenLayers.Bounds(-58.1754,-35.057,-53.3249,-31.5083),
	                maxResolution: 156543.0339,
	                numZoomLevels: 22,
	                units: 'm',
	                projection: new OpenLayers.Projection("EPSG:900913"),
	                displayProjection: new OpenLayers.Projection("EPSG:4326")
	            } );

			var uruguayLayer = new OpenLayers.Layer.OSM("Uruguay Tiles", "http://192.168.33.10/uruguay/${z}/${x}/${y}.png", {numZoomLevels: 22,
                tileOptions: {
                	"crossOriginKeyword": null
                }});
            map.addLayer(uruguayLayer);
            
			var switcherControl = new OpenLayers.Control.LayerSwitcher();
            map.addControl(switcherControl);
            switcherControl.maximizeControl();
			if( ! map.getCenter() ){
                var lonLat = new OpenLayers.LonLat(lon, lat).transform(new OpenLayers.Projection("EPSG:4326"), map.getProjectionObject());
                map.setCenter (lonLat, 12);
            }
		}
		</script>
	</head> 
	<body onload="init()">
		<div id="map" style="width:100%; height:80%; position: relative; overflow: hidden;"></div>
	</body>
</html>
