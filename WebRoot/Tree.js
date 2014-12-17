$(document).ready(function(){
function getTree() {
    var tree = [
      {
      text: "物理对象",
      nodes: [
        {
          text: "美元",
          nodes: [
            {
              text: "五成新美元",
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
              text: "七成新美元"
            }
          ]
        },
        {
          text:"欧元"
        },
        {
          text: "支票",
          nodes:[
                {
                  text:"大尺寸支票"
                },
                {
                  text:"中尺寸支票"
                },
                {
                  text:"小尺寸支票"
                }
          ]
        }
]
}
]
          return tree;
      }
$('#tree').treeview({data: getTree()});
});
