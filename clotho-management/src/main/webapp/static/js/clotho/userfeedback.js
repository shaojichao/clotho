var centerPanel = Ext.create('Ext.grid.Panel', {
	region: 'center',
	title: '列表 <span style="color:red">(双击查看"反馈内容")</span>',
    columns: [
		        {header: 'ID',  dataIndex: 'id', width: 60,sortable:true },
		        {header: 'SuperD硬件设备ID', dataIndex: 'hwid', width: 150},
		        {header: '终端设备的唯一标识', dataIndex: 'udid', width: 250},
		        {header: '联系方式',  dataIndex: 'contact', width: 100,sortable:true },
		        {header: '反馈内容',  dataIndex: 'content', width: 300,sortable:true },
		        {header: 'wifimac',  dataIndex: 'wifimac', width: 60,sortable:true },
		        {header: 'wirelesssmac',  dataIndex: 'wirelesssmac', width: 60,sortable:true },
		        {header: 'wiremac',  dataIndex: 'wiremac', width: 60,sortable:true },
		        {header: '系统版本',  dataIndex: 'osver', width: 60,sortable:true },
		        {header: '设备类型',  dataIndex: 'device', width: 60,sortable:true,renderer:function(value){
			        	if(value=='1'){
			        		return "Android";
			        	}else if(value=='2'){
			        		return "IOS";
			        	}else{
			        		return value;
			        	}
		        	},
		        },
		        {header: '地域',  dataIndex: 'area', width: 60,sortable:true },
		        {header: '语言',  dataIndex: 'language', width: 60,sortable:true },
		        {header: 'imei',  dataIndex: 'imei', width: 60,sortable:true },
		        {header: 'idfv',  dataIndex: 'idfv', width: 60,sortable:true },
		        {header: '应用唯一标识',  dataIndex: 'appkey', width: 60,sortable:true },
		        {header: '应用版本',  dataIndex: 'appver', width: 60,sortable:true },
		        {header: '用户唯一标识',  dataIndex: 'uid', width: 60,sortable:true },
		        {header: 'devicebrand',  dataIndex: 'devicebrand', width: 60,sortable:true },
		        {header: 'devicedevice',  dataIndex: 'devicedevice', width: 60,sortable:true },
		        {header: 'devicemodel',  dataIndex: 'devicemodel', width: 60,sortable:true },
		        {header: 'devicehardware',  dataIndex: 'devicehardware', width: 60,sortable:true },
		        {header: 'deviceid',  dataIndex: 'deviceid', width: 60,sortable:true },
		        {header: 'deviceserial',  dataIndex: 'deviceserial', width: 60,sortable:true },
		        {header: '设备分辨率',  dataIndex: 'ro', width: 60,sortable:true },
		        {header: '分发渠道',  dataIndex: 'channel', width: 60,sortable:true },
		        {header: '设备本地时间（毫秒）',  dataIndex: 'dts', width: 60,sortable:true },
		        {header: '创建时间',  dataIndex: 'createTime', width: 160,sortable:true,renderer:function(value){
		        	if(value != null){
		        		return Ext.util.Format.date(new Date(value),'Y-m-d H:i:s');
		        	}else{
		        		return '';
		        	}
	        	}
	        }
		        
		     ],
	store: Ext.create('Ext.data.JsonStore', {
		autoLoad: true,
		storeId: 'centerStore',
		pageSize: 20,
	    fields :['id', 'hwid','udid','wifimac','wirelesssmac','wiremac','os','osver',
	             'device','area','language','imei','idfv','appkey','appver','uid','devicebrand',
	             'devicedevice','devicemodel','devicehardware','deviceid','deviceserial','ro',
	             'channel','dts','contact','content','createTime'],
	    proxy: {
	        type: 'ajax',
	        url: path + '/clotho/userfeedback/list.do',
	        reader: {
	            type: 'json',
	            totalProperty: "result",
	            root: 'rows'
	        }
	    }
	}),
	bbar: Ext.create('Ext.toolbar.Paging', {//xtype: pagingtoolbar
        store: Ext.data.StoreManager.get('centerStore'),
        displayInfo: true,
        displayMsg: '第{0}-{1}条，共{2}条',
        emptyMsg: "没有数据",
        beforePageText: '第',
        afterPageText: '页，共 {0} 页'
    }),
    listeners: {
    	dblclick: {
            element: 'body', //bind to the underlying body property on the panel
            fn: function(){ 
            	var models = centerPanel.getSelectionModel().getSelection();
        		Ext.Msg.alert('反馈内容', models[0].get('content'));
            }
        } 
    }
});

//domReady
Ext.onReady(function(){
	Ext.create('Ext.container.Viewport', {
		layout: 'border',
		items:[centerPanel]
	});
});