import {Map} from '../Maps.js'

export class drawKakaoMap extends Map{
  constructor() {
    super();
    this.type='kakao'
  }
  init = function(divId){
    this.divId = divId
    this.map = new kakao.maps.Map(document.getElementById(divId), this.options);
  }
  options = { //지도를 생성할 때 필요한 기본 옵션
    center: new kakao.maps.LatLng(this.initOptions.latitude, this.initOptions.longitude), //지도의 중심좌표.
    level: 6 //지도의 레벨(확대, 축소 정도)
  }
  bounds={}
  addSseEventListener = function(evt){
    evt.addEventListener('periodic-event', (evt) => {
      const data = JSON.parse(evt.data);
      this.reDraw(data);
    })
  }
  draw = function (dataList){
    try{
      dataList.forEach(data=>{
        /*const marker = drawKakaoMap.createMarker(data);
        drawKakaoMap.markerList.push({id:data.CAR_SEQ , marker});
        drawKakaoMap.showMarker(marker);*/
        let position = this.convertWsg84ToPoint(data.LAT, data.LON);
        this.createNode(data, position);
      })
    }catch (e){
      console.log(e);
    }
  }
  createNode(data, position) {
    const imageOverlay = this.createImageOverlay(data, this.createImageContent(data));
    imageOverlay.setPosition(position);
    this.nodeListPush(data.CAR_SEQ, imageOverlay)
    this.show(imageOverlay)
  }
  reDraw = function (dataList){
    dataList.forEach(data => {
      /*const carMarker = drawKakaoMap.markerList.find(item=> item.id = data.CAR_SEQ);
      if(carMarker){
        carMarker.marker.setPosition(drawKakaoMap.convertWsg84ToPoint(data.LAT,data.LON))
      }else{
        const newMarker = drawKakaoMap.createMarker(data);
        drawKakaoMap.markerList.push(newMarker)
        drawKakaoMap.showMarker(newMarker);
      }*/
      const node = this.nodeList.find(item => item.id === data.CAR_SEQ);
      let position = this.convertWsg84ToPoint(data.LAT, data.LON);
      if (node) {
        node.overlay.setPosition(position)
      }else{
        this.createNode(data, position);
      }
      //this.bounds.extend(position)
    })
    //drawKakaoMap.fitBound();
  }
  fitBound = function(){
    this.map.setBounds(this.bounds);
  }
  nodeListPush = function(CAR_SEQ, overlay){
    this.nodeList.push({id: CAR_SEQ, overlay})
  }
/*  createMarker: (data)=>{
    try {
      const markerPosition  = drawKakaoMap.convertWsg84ToPoint(data.LAT, data.LON);
      // 마커를 생성합니다
      const marker = new kakao.maps.Marker({
        position: markerPosition,
        //image: createMarkerImage(data)
      });
      return marker;
    }catch (e){
      console.log(e);
    }
  },
  createMarkerImage: (data) => {
    const markerImageSrc = `${data.arrowColor}_circle.png`;
    const markerImageSize = new kakao.maps.Size(23, 23),
      imageOptions = {
        spriteOrigin: new kakao.maps.Point(10, 0),
        spriteSize: new kakao.maps.Size(36, 98)
      };
    return new kakao.maps.MarkerImage("/image/circle-arrows/" +markerImageSrc, markerImageSize, imageOptions);
  },*/
  convertWsg84ToPoint= function(LAT, LON){
    return new kakao.maps.LatLng(LAT, LON);
  }
  show = function(object){
    if(this.map){
      object.setMap(this.map);
    }
  }
  createImageContent = function(data){
    const imageSrc = this.getImageSrc(data);
    return `<img class="bic_marker" src="${imageSrc}" style="transform:rotate(${data.course}deg); width: 23px; height:23px;">`;
  }
  createImageOverlay = function(data, content) {
    return  new kakao.maps.CustomOverlay({
      content:  content,
      position: this.convertWsg84ToPoint(data.LAT,data.LON),
      /*xAnchor: 0.5,
      yAnchor: 1,*/
      zIndex: 3
    });
  }
  convertSatellite = function(){
    this.map.setMapTypeId(kakao.maps.MapTypeId.HYBRID);
  }
  destroy() {
    const divMap = document.getElementById(this.divId)
    divMap.innerHTML=''

  }
}
