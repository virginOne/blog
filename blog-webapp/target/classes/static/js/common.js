var vAside = new Vue({
    el: '#aside',
    data:{
        headPhoto:'../image/head/default.jpg',
        userName:'游客',
        menus:[{name:'首页',url:'javascript:void(0);'},{name:'用户信息',url:'javascript:void(0);'},{name:'注销',url:'javascript:void(0);'}],
        address:'',
    },
    mounted: function () {
        // 获取地理位置(手机端)
        if(decideDevice()){
            if(navigator.geolocation){
                navigator.geolocation.getCurrentPosition(function(position){
                    console.log(position)
                },function(error){
                    console.log(error);
                })
            }
        }
        //添加全局触摸拉出侧边栏
        if (document.addEventListener) {
            let touch_x, touch_y;
            let changedX, changedY;
            let sourceLeft = document.defaultView.getComputedStyle(this.$el).left;
            document.addEventListener('touchstart', e => {
                touch_x = e.changedTouches[0].screenX;
                touch_y = e.changedTouches[0].screenY;
            });
            document.addEventListener('touchmove', e => {
                changedX = e.changedTouches[0].screenX - touch_x;
                changedY = e.changedTouches[0].screenY - touch_y;
                if (changedX > 0 && changedX <= -1 * Number(sourceLeft.replace('px', ''))) {
                    this.$el.style.setProperty('left', (changedX + Number(sourceLeft.replace('px', ''))) + 'px');
                } else if (changedX < 0 && changedX >= Number(sourceLeft.replace('px', ''))) {
                    this.$el.style.setProperty('left', changedX + 'px');
                }
            });
            document.addEventListener('touchend', e => {
                changedX = e.changedTouches[0].screenX - touch_x;
                changedY = e.changedTouches[0].screenY - touch_y;
                if (changedX > 0) {
                    this.$el.style.setProperty('left', 0);
                } else if(changedX<0){
                    this.$el.style.setProperty('left', sourceLeft);
                }
            });
            document.addEventListener('touchcancel', e => {
                changedX = e.changedTouches[0].screenX - touch_x;
                changedY = e.changedTouches[0].screenY - touch_y;
                if (changedX > 0) {
                    this.$el.style.setProperty('left', 0);
                } else if(changedX<0){
                    this.$el.style.setProperty('left', sourceLeft);
                }
            });
        }
    }
});

//动态效果


//toTop


function toTop(){
    window.scrollTo(0,0);
}

function decideDevice() {
    if( navigator.userAgent.match(/Android/i)
    || navigator.userAgent.match(/webOS/i)
    || navigator.userAgent.match(/iPhone/i)
    || navigator.userAgent.match(/iPad/i)
    || navigator.userAgent.match(/iPod/i)
    || navigator.userAgent.match(/BlackBerry/i)
    || navigator.userAgent.match(/Windows Phone/i)
    ){
        return true;
    }
    else {
        return false;
    }
}