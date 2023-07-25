export class Map {
  constructor() {
  }
  d = {
    latitude:'LAT',
    longitude:'LON',
    id:'CAR_SEQ'
  }
  initOptions ={
    latitude: 37.46684335194588,
    longitude: 126.4445336671607,
    kakao:{zoom:6},
    openlayers:{zoom:14.666633333333337, rotation:-0.9515266582052867, maxZoom:16, minZoom:4},
    naver:{}
  }
  options
  init(divId, dataList){}
  draw(dataList){}
  reDraw(dataList){}
  //지도에 표시 되어지는 장비의 이미지 생성
  createNode(data){}
  createMarkerLayer(markerLayerName){}
  createOverlayLayer(overlayLayerName){}
  getZoom(){}
  setZoom(){}
  getPosition(){}
  setPosition(){}
  getBound(){}
  setBound(){}
  setCenter(){}
  movePosition(){}
  convertWsg84ToPoint(LAT,LON){}
  addLayer(layer){}
  addSseEventListener(evt){}
  getImageSrc = function (data) {
    const image =   data.arrowColor || 'red';
    return `/assets/image/maps_image/${image}_circle.png`;
  }
  convertSatellite(){}
  updateMap(){}
  destroy(){}
  map
  //marker 지원
  nodeList = []
  //overlay 지원 가능한 point
  overlayList = []

}
