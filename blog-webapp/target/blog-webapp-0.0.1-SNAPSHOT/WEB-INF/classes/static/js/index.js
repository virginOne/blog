(function(){
    var artcles=new Vue({
        el:'#artcles',
        data:{
            status:'200',
            artcles:[{title:'Vue的简单Demo',artcleID:"1",describle:"这是一个简单的VueDemo"},{url:""}]
        },
        methods:{
            viewArtcle:function(e){
                this.$el.style.setProperty("transform","scale(0,0)");
                this.$el.style.setProperty("position","absolute");
                document.getElementById("artcleContent").style.setProperty("transform","scale(1,1)");
                document.getElementById("artcleContent").style.setProperty("position","static");
                return ;
            }
        }
    });
    artcles.artcles[1].url="456";
})()