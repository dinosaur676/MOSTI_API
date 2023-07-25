import './style.css';
import {Map, View} from 'ol';
import TileLayer from 'ol/layer/Tile';
import OSM from 'ol/source/OSM';
import Feature from 'ol/Feature';
import Point from 'ol/geom/Point';
import {circular} from 'ol/geom/Polygon';
import {Icon, Style} from 'ol/style';
import {fromLonLat, toLonLat} from 'ol/proj';
import VectorSource from 'ol/source/Vector';
import {
  DragRotateAndZoom,
  defaults as defaultInteractions,
} from 'ol/interaction';
import {testData} from './testData';
import VectorLayer from "ol/layer/Vector";
import {getVectorContext} from 'ol/render';
import XYZ from 'ol/source/XYZ.js';
import TileWMS from 'ol/source/TileWMS.js';
import {Map as Maps} from '../Maps'


export class drawOpenMap extends Maps {
  constructor() {
    super();
    this.type = 'openlayers'
  }

  imageOverLayLis = []
  vectorSource = {}
  vectorLayer = {}
  init = function (divId) {
    const localMapTarget = document.getElementById(divId);
    this.map = new Map({
      interactions: defaultInteractions().extend([new DragRotateAndZoom()]),
      target: localMapTarget,
      layers:
        [this.createNormalMap()],

      view:new View({
        rotation: this.initOptions[this.type].rotation,
        center: fromLonLat([this.initOptions.longitude, this.initOptions.latitude]),
        zoom: this.initOptions[this.type].zoom,
        maxZoom: this.initOptions.maxZoom,
        minZoom: this.initOptions.minZoom
      })
    })
  }
  getInfo= function(){
    console.log(this.map.getView().getZoom())
    console.log(this.map.getView().getRotation())
    console.log(toLonLat(this.map.getView().getCenter()) )
  }
  bounds = {}
  addSseEventListener = function (evt) {
    evt.addEventListener('periodic-event', (evt) => {
      const data = JSON.parse(evt.data);
      this.reDraw(data);
    })
  }
  reDraw = function (dataList) {
    dataList.forEach(data => {
      const node = this.nodeList.find(item => data[this.d.id] === item.id);
      if (node) {
        this.updateStyle(node.feature, data);
        node.feature.setGeometry(new Point(this.convertWsg84ToPoint(data[this.d.latitude], data[this.d.longitude])));
      } else {
        this.createNode(data);
      }
    });
    if (Object.values(this.vectorLayer).length === 0 && this.nodeList.length > 0) {
      this.createNodeVectorLayer()
      this.map.addLayer(this.vectorLayer);
    }
  }
  draw = function (dataList) {
    dataList.forEach(data => {
      this.createNode(data);
    });
    this.createNodeVectorLayer()
  }
  createNodeVectorLayer = function () {
    this.createVectorSource();
    this.createVectorLayer("carNodes");
  }
  createNode = function (data) {
    const feature = this.createFeature(data);
    feature.setStyle(this.crateStyle(data));
    this.nodeListPush(data[this.d.id], feature);
  }
  nodeListPush = function (CAR_SEQ, feature) {
    const findFeatureIndex = this.nodeList.findIndex(item => item.id === CAR_SEQ);
    if (findFeatureIndex< 0) {
      this.nodeList.push({id: CAR_SEQ, feature});
    }else{
      this.nodeList[findFeatureIndex] = feature;
    }
  }
  createVectorSource = function () {
    if (Object.keys(this.vectorSource).length === 0) {
      this.vectorSource = new VectorSource({
        features: this.nodeList.map(item => item.feature)
      })
    }
  }
  createVectorLayer = function (name) {
    if (Object.keys(this.vectorLayer).length === 0) {
      this.vectorLayer = new VectorLayer({
        source: this.vectorSource,
        properties: {
          name: name
        }
      })
    }
  }
  createFeature = function (data) {
    return new Feature({
      geometry: new Point(this.convertWsg84ToPoint(data[this.d.latitude], data[this.d.longitude]))
    });
  }
  convertWsg84ToPoint = function (LAT, LON) {
    return fromLonLat([LON, LAT]);
  }
  crateStyle = function (data) {
    return new Style({
      image: this.createImageSrc(data)
    });
  }
  updateStyle = function (feature, data) {
    const style = feature.getStyle()
    const image = style.getImage()
    image.setRotation(this.map.getView().getRotation())
  }
  createImageSrc = function (data) {
    const rotate = this.map.getView().getRotation();
    //console.log(6.3 * data.course / 360 + rotate)
    return new Icon({
      //color: '#8959A8',
      crossOrigin: 'anonymous',
      // For Internet Explorer 11
      scale: 0.2,
      //rotation: data.course,
      rotation:  rotate,
      src: this.getImageSrc(data),
    });
  }
  convertSatellite = function(){
    console.log(this.map.getLayers())
    const satelliteLayer = this.map.getLayers().array_.find(item => item.get("name") === 'satellite')

    if(!satelliteLayer){
      this.map.removeLayer(this.map.getLayers().array_.find(item => item.get("name") === 'normal'))
      this.createSatelliteMap().forEach((item,index)=>{
        this.map.getLayers().insertAt(index,item)
      })
    }

  }
  createNormalMap = function(){
    return new TileLayer({
      source: new OSM(),
      properties: {
        name: 'normal'
      }
    })
    /*return new TileLayer({
      source: new XYZ({
        //Vworld Tile 변경
        url: 'http://xdworld.vworld.kr:8080/2d/Base/202002/{z}/{x}/{y}.png',
        crossOrigin:'anonymous'
      }),
      properties: {
        name: 'normal'
      }
    })*/
  }
  createSatelliteMap = function(){
    const attr = '&copy; <a href="http://dev.vworld.kr">vworld</a>';
    return [new TileLayer({
      source: new XYZ({
        //Vworld Tile 변경
        //url: 'http://xdworld.vworld.kr:8080/2d/Satellite/service/{z}/{x}/{y}.jpeg',
        url: 'http://api.vworld.kr/req/wmts/1.0.0/529E8BEF-C2E7-361C-AC06-F650DAF5478E/Satellite/{z}/{y}/{x}.jpeg',
        crossOrigin:'anonymous',
        attributions : attr
      }),
      properties: {
        name: 'satellite'
      }
    }),
      new TileLayer({
        source: new XYZ({
          //Vworld Tile 변경
          //url: 'http://xdworld.vworld.kr:8080/2d/Hybrid/202002/{z}/{x}/{y}.png',
          url: 'http://api.vworld.kr/req/wmts/1.0.0/529E8BEF-C2E7-361C-AC06-F650DAF5478E/Hybrid/{z}/{y}/{x}.png',
          crossOrigin:'anonymous',
          attributions : attr
        }),
        properties: {
          name: 'hybrid'
        }
      })
    ]
  }
  destroy() {
    this.map.setTarget(null)
  }

  /*createSatelliteLayer = function(){
    const satelliteLayer = new TileLayer({
      source: new XYZ({
        attributions: ['Powered by Esri',
          'Source: Esri, DigitalGlobe, GeoEye, Earthstar Geographics, CNES/Airbus DS, USDA, USGS, AeroGRID, IGN, and the GIS User Community'],
        attributionsCollapsible: false,
        url: 'https://services.arcgisonline.com/ArcGIS/rest/services/World_Imagery/MapServer/tile/{z}/{y}/{x}',
      }),
      properties:{
        name: 'satellite'
      }
    });
    return satelliteLayer;
  }
  createNasaWMS = function () {
    return new TileLayer({
      extent: [-13884991, 2870341, -7455066, 6338219],
      source: new TileWMS({
        url: 'https://svs.gsfc.nasa.gov/cgi-bin/wms',
        params: {'LAYERS': 'topp:states', 'TILED': true},
        //serverType: 'geoserver',
        // Countries have transparency, so do not fade tiles:
        //transition: 0,
      }),
    })
  }*/

}


