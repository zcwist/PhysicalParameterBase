$(document).ready(function(){
    var data = {
      	name: 'root',
      	oid: null,
      	children: []
    }



    Vue.component('item', {
    	template: '#item-template',
      	data: function () {

	        return {
	          	open: false
	        }
      	},
      	computed: {
	        isFolder: function () {
	          	return typeof(this.model.oid)!='string'
        	},
        	childrenIsLoaded: function() {
        		return this.model.children &&
            		this.model.children.length
        	}

      	},
      	methods: {
	        toggle: function () {
	        	if (this.isFolder){
	        		if (!this.childrenIsLoaded){
	        			//the children of the node hasn't been loaded
	        			this.changeType();
	        			var nodeName = (this.model.name == 'root')? "":this.model.name;
	        			this.loadTreeData(nodeName,this);
	        		}
	        		this.open = !this.open
	        	}
	        	else {
	        		// the node is a leaf
	        		generateTable(this.model);
	        	}
        	},
        	changeType: function () {
          		
        		this.model.$add('children', [])
        		this.open = true
          		
        	},
        	addChild: function (nodeName,oid) {
          		this.model.children.push({
            		name: nodeName,
            		oid:oid
          		})
       		},
       		loadTreeData: function(nodeName,model){
       			$.post("servlet/QueryServlet",{"askFor":"objectTree","parentNode":nodeName},function(result){
        			$.each(result.child, function(index,val){
        				model.addChild(val._id,val.oid);
        				
        			})
        			this.open = true;
	        	})
       		}
      	}
    })

	var tableDataSample = '[{"oid":"1","value":{"atType":"杨氏模量","unit":"GPa","value":0.655,"type":"float"},"pid":"1"}]';

	var generateTable = function(nodeModel){
		var nodeName = nodeModel.name;
		$('#table-title').html(nodeName+"物性参数表");
		var objectId = nodeModel.oid;
		console.log("oid:" + objectId + "   nodeName:" + nodeName);
		$.post("servlet/QueryServlet",{"askFor":"recordTable","objectId":objectId},function(result){
			var tableHtml = '';
			$.each(result, function(index,val){
				console.log(val);
				var parameterId = val.pid;

				// $.each(val.value,function(index, val){
					tableHtml += generateTr(val.value,objectId,parameterId);

				// })

			});
			$('#recordContent').html(tableHtml);
		})

	}

	var generateTr = function(itemValue,oid,pid){
		var tr = '<tr>' + 
			'<td>' + itemValue.atType + '</td>' +
			'<td>' + itemValue.value + '</td>' +
			'<td>' + itemValue.unit + '</td>' +
			'<td><a href="RecordSample.html?oid=' + oid + '&pid=' + pid +'">more</a></td>' +
			'</tr>';
		return tr;
	}

    // boot up the demo
    var demo = new Vue({
      	el: '#demo',
      	data: {
        	treeData: data
      	}
    });



});