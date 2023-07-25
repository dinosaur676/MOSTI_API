import {Map} from "../Maps.js"

export class drawNaverMap extends Map{
  constructor() {
    super();
    this.type='naver'
  }
  map = {}
  options ={
    // center: new naver.maps.LatLng(37.54222186343151, 127.12866892591039),
    useStyleMap: true,
    mapTypeControl: true,
    mapTypeControlOptions: {
      style: naver.maps.MapTypeControlStyle.BUTTON,
      position: naver.maps.Position.TOP_RIGHT
    },
    zoomControl: true,
    zoomControlOptions: {
      position: naver.maps.Position.RIGHT_BOTTOM
    },
    zoom: 7
  }
  init (divId){
    this.map = new naver.maps.Map(document.getElementById(divId), this.options);
  }
  convertSatellite = function(){
    alert("작업중입니다.")
  }
  destroy() {
    this.map.destroy()
  }
  addSseEventListener(){
    alert("작업중입니다.")
  }
}
