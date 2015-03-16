$(document).ready(function(){
function getTree() {
    var tree = [
      {
      text: "物理对象",
      nodes: [
        {
          text: "介质类型:美元",
          nodes: [
            {
              text: "新旧程度:５",
              nodes: [
                {
                  text:"5美元"
                },
                {
                  text:"10美元"
                }
              ]
            },
            {
              text: "新旧程度:７"
            }
          ]
        },
        {
          text:"欧元"
        },
        {
          text: "介质类型:支票",
          nodes:[
                {
                  text:"尺寸:大"
                },
                {
                  text:"尺寸:中"
                },
                {
                  text:"尺寸:小"
                }
          ]
        }
      ]
      }
    ];
          return tree;
}

$.post("servlet/QueryServlet",{parentNode:""},function(result){
  // $('#tree').text(result);
  console.log(result);
  $.each(result, function(index,val){
    console.log(index + "   " + val);

  })
})

// $('#tree').treeview({data: getTree()});
// $('#tree').on('nodeSelected',function(event,node){
//   if (node._nodes == null)
//   console.log(node);

// });
});
