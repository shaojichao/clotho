var topPanel = Ext.create('Ext.toolbar.Toolbar', {
		region : 'north',
		items : [
		         '-',
		         '当前用户: '+ adminName,
		         '-',
		         {
		        	 text: '退出系统'	,
		        	 handler: function(){
		        		 Ext.Msg.confirm("系统信息","是否确认退出系统?",function(text){
		        			 	if(text == 'no')
		        			 		return;
					
								Ext.Ajax.request({    
									url: path + '/login/logout.jsp',
									success:function(response,options){
										top.location.href = path;
									}
								});  
		        		 })
		        	 }
		         }
		    ]
});

//左侧菜单栏
var westPanel = Ext.create('Ext.tree.Panel', {
	region: 'west',
	title: '升级包管理',
	width: 150,
	collapsible: true,
	rootVisible: false,
	split: true,
	store : Ext.create('Ext.data.TreeStore', {
		storeId: 'menuStore',
		fields: ['id', 'text', 'leaf', 'url'],
		proxy: {
			type: 'ajax',
			url: path + '/menu/list.do?adminID=' + adminID
		},
		listeners: {
			load: function(store, records){
				for(var i = 0;i < records.length;i++){
					records[i].data.url = records[i].data.href;
					delete records[i].data.href;
				}
			}
		}
	}),
	listeners: {
		itemclick: function(rowModel, record, index, eOpts){
			var tabId = 'tab_' + record.data.id,
				tab = centerPanel.getComponent(tabId);
			if(!tab){
				tab = createPanel(tabId, record.data.text, record.data.url);
				centerPanel.add(tab);
			}
			centerPanel.setActiveTab(tab);
		}
	}
});

//主内容区域
var centerPanel = Ext.create('Ext.tab.Panel', {
	region: 'center',
	activeTab: 0,
	items: {
		title: '首页',
		html: '<center>后台管理首页</center>'
	}
});

//
Ext.onReady(function(){
	Ext.create('Ext.container.Viewport', {
		layout: 'border',
		items: [topPanel, westPanel, centerPanel]
	});
});

//创建内容显示面板
function createPanel(id, title, href){
	return Ext.create('Ext.Panel', {
		id: id,
		title : title,
		autoScroll : false,
		closable : true,
		border: false,
		html : '<iframe id="' + id + '" src="' + href + '" scrolling="no" frameborder="0" style="width:100%; height:100%;"></iframe>'
	});
}
