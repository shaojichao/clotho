var _upgradeid;
var upgradePop = Ext.create('Ext.window.Window', {
	id: 'upgradeWin',
    title: '增加',
    height: 370,
    width: 480,
    bodyPadding: 5,
    maximizable: true,
    modal: true,
    closeAction: 'hide',
    overflowX: 'hidden',
    overflowY: 'auto',
    items: [
            Ext.create('Ext.form.Panel', {
            	id: 'upgradeForm',
            	width: 460,
            	height: 250,
                url: path + '/clotho/upgrade/saveVersion.do',
                layout: 'anchor',
                defaults: {
                    anchor: '80%',
                    labelAlign: 'right',
                    labelWidth: 80,
                    blankText: '必填项'
                },
                // The fields
                defaultType: 'textfield',
                items: [{//隐藏域
                			name: 'id',
                			hidden: true
                		},{
                			name: 'serialno',
                			id: 'serialno',
                			hidden: true
                		},{
                			name: 'createby',
                			hidden: true
                		},{
                			name: 'updateby',
                			hidden: true
                		},{
            		        fieldLabel: '版本号',
            		        name: 'version',
            		        allowBlank: false,
            		        maxLength: 40,
            		        maxLengthText: '不能超过20个字符'
                		},{
                			xtype: 'combo',
							name: 'clientid',
							fieldLabel: '客户端类型',
							store: Ext.create('Ext.data.ArrayStore', {
				       			fields: ['value', 'text'],
				       			data: [[1,'Android'],[2,'IOS'],[3,'AndroidPad']]
					   		}),
					   		displayField: 'text',
					   	    valueField: 'value',
                		},{
                			fieldLabel: '是否提示',
                	        xtype: 'fieldcontainer',
                	        defaultType: 'radiofield',
            	            layout: 'hbox',
            	            items: [
            	                {
            	                    boxLabel: '不提示',
            	                    name: 'showtype',
            	                    inputValue: '1',
            	                    checked: true
            	                }, {
            	                    boxLabel: '提示',
            	                    name: 'showtype',
            	                    inputValue: '2'            	                    
            	                }
            	            ]
                		},{
                			fieldLabel: '升级类型',
                	        xtype: 'fieldcontainer',
                	        defaultType: 'radiofield',
            	            layout: 'hbox',
            	            items: [
            	                {
            	                    boxLabel: '可选升级',
            	                    name: 'upgradetype',
            	                    inputValue: '1',
            	                    checked: true
            	                }, {
            	                    boxLabel: '强制升级',
            	                    name: 'upgradetype',
            	                    inputValue: '2'            	                    
            	                }
            	            ]
                		},{
            	    	   fieldLabel: '版本描述',
            	    	   name: 'memo',
            	    	   xtype: 'textarea',
            	    	   height: 100,
            	    	   allowBlank: false
            	       },{
           		        fieldLabel: '升级包地址',
        		        name: 'pkgurl',
        		        id: 'pkgurl',
        		        allowBlank: false
            		}]
            }),
            //覆盖升级包的选择上传功能
            Ext.create('Ext.form.Panel', {
            	url: path + '/upload/upload.do',
            	width: 560,
            	height: 27,
            	layout: 'hbox',
            	id: 'pkgUploadForm',
            	items: [
            	        {
            			   fieldLabel: '升级包',
            			   labelWidth: 80,
            			   labelAlign: 'right',
            			   xtype: 'textfield',
            			   width: 320,
            			   margin: '0 5 0 0',
            			   id: 'pkgText',
            			   readOnly: true
            			},{
						   xtype: 'filefield',
						   buttonText: '浏览',
						   name: 'pkg',
						   buttonOnly: true,
						   listeners: {
							   change: function(fileField, string, eOpts){
								   Ext.getCmp('pkgText').setValue(string);
								   Ext.getCmp('pkgUploadBtn').setDisabled(false);
							   }
						   }
						},{
            	        	xtype: 'button',
            	        	id: 'pkgUploadBtn',
            	        	text: '上传',
            	        	disabled: true,
            	        	margin: '0 0 0 10',
            	        	handler: function(){
            	        		Ext.getCmp('pkgUploadForm').getForm().submit({
            	        			waitTitle: '系统提示',
            	        			waitMsg: '升级包上传中，请稍候......',
            	        			success: function(form, action){
            	        				Ext.getCmp('pkgUploadBtn').setDisabled(true);
            	        				Ext.getCmp('pkgurl').setValue(action.result.msg);
            	        				Ext.Msg.alert('系统提示', '升级包上传成功！');
            	        			}
            	        		});
            	        	}
            	        }
            	]
            })
    ],
    buttons: [
        {
        	text: '保存',
        	handler: function(){
        		
        		if(!Ext.getCmp('upgradeForm').getForm().isValid()){
        			return;
        		}
        		
        		Ext.getCmp('upgradeForm').submit({
        			waitTitle: '系统提示',
        			waitMsg: '保存中......',
        			success: function(form, action){
        				Ext.getCmp('upgradeWin').hide();
        				Ext.getCmp('pkgUploadForm').getForm().reset();
        				centerPanel.getStore().reload();
        				Ext.Msg.alert('系统提示', '保存成功！');
        			},
        			failure: function(form, action){
        				Ext.Msg.alert('系统提示', action.result.msg);
        			}
        		});
        	}
        },{
        	text: '取消',
        	handler: function(){
        		upgradePop.hide();
        	}
        }
    ]
});

var planPop = Ext.create('Ext.window.Window', {
	id: 'planWin',
    title: '增加',
    height: 370,
    width: 480,
    bodyPadding: 5,
    maximizable: true,
    modal: true,
    closeAction: 'hide',
    overflowX: 'hidden',
    overflowY: 'auto',
    items: [
            Ext.create('Ext.form.Panel', {
            	id: 'planForm',
            	width: 460,
            	height: 250,
                url: path + '/clotho/upgrade/savePlan.do',
                layout: 'anchor',
                defaults: {
                    anchor: '80%',
                    labelAlign: 'right',
                    labelWidth: 80,
                    blankText: '必填项'
                },
                // The fields
                defaultType: 'textfield',
                items: [{//隐藏域
                			name: 'id',
                			hidden: true
                		},{
                			name: 'originid',
                			id: 'originid',
                			hidden: true
                		},{
                			name: 'upgradeid',
                			id: 'upgradeid',
                			hidden: true
                		},{
                			name: 'createby',
                			hidden: true
                		},{
                			name: 'updateby',
                			hidden: true
                		},{
            		        fieldLabel: 'From版本号',
            		        name: 'version',
            		        allowBlank: false
                		},{
                			xtype: 'combo',
							name: 'clientid',
							fieldLabel: '客户端类型',
							store: Ext.create('Ext.data.ArrayStore', {
				       			fields: ['value', 'text'],
				       			data: [[1,'Android'],[2,'IOS'],[3,'AndroidPad']]
					   		}),
					   		displayField: 'text',
					   	    valueField: 'value',
                		},{
                			fieldLabel: '是否提示',
                	        xtype: 'fieldcontainer',
                	        defaultType: 'radiofield',
            	            layout: 'hbox',
            	            items: [
            	                {
            	                    boxLabel: '不提示',
            	                    name: 'showtype',
            	                    inputValue: '1',
            	                    checked: true
            	                }, {
            	                    boxLabel: '提示',
            	                    name: 'showtype',
            	                    inputValue: '2'            	                    
            	                }
            	            ]
                		},{
                			fieldLabel: '升级类型',
                	        xtype: 'fieldcontainer',
                	        defaultType: 'radiofield',
            	            layout: 'hbox',
            	            items: [
            	                {
            	                    boxLabel: '可选升级',
            	                    name: 'upgradetype',
            	                    inputValue: '1',
            	                    checked: true
            	                }, {
            	                    boxLabel: '强制升级',
            	                    name: 'upgradetype',
            	                    inputValue: '2'            	                    
            	                }
            	            ]
                		},{
            	    	   fieldLabel: '版本描述',
            	    	   name: 'memo',
            	    	   xtype: 'textarea',
            	    	   height: 100,
            	    	   allowBlank: false
            	       }]
            })            
    ],
    buttons: [
        {
        	text: '保存',
        	handler: function(){
        		Ext.getCmp('upgradeid').setValue(_upgradeid);
        		var planForm = Ext.getCmp('planForm').getForm();
        		if(!planForm.isValid()){
        			return;
        		}
        		
       			planForm.submit({
        			waitTitle: '系统提示',
        			waitMsg: '保存中......',
        			success: function(form, action){
        				if(!action.result.success){
        					Ext.Msg.alert('系统提示', action.result.msg);
        				}else{
	        				Ext.getCmp('planWin').hide();
	        				eastPanel.getStore().reload();
	        				Ext.Msg.alert('系统提示', '保存成功！');
        				}
        			},
        			failure: function(form, action){
        				Ext.Msg.alert('系统提示', action.result.msg);
        			}
        		});
        	}
        },{
        	text: '取消',
        	handler: function(){
        		planPop.hide();
        	}
        }
    ]
});

var centerPanel = Ext.create('Ext.grid.Panel', {
	region: 'center',
	title: '升级包列表',
    columns: [
		        {header: 'ID',  dataIndex: 'id', width: 60,sortable:true },
		        {header: '版本号', dataIndex: 'version', width: 80},
		        {header: '时间序列号', dataIndex: 'serialno', width: 120},
		        {header: '版本描述',  dataIndex: 'memo', width: 300,sortable:true },
		        {header: '下载地址',  dataIndex: 'pkgurl', width: 300,sortable:true },
		        {header: '客户端类型',  dataIndex: 'clientid', width: 85,sortable:true,renderer:function(value){
			        	if(value=='1'){
			        		return "Android";
			        	}else if(value=='2'){
			        		return "IOS";
			        	}else if(value=='3'){
			        		return 'AndroidPad';
			        	}else{
			        		return value;
			        	}
		        	},
		        },
		        {header: '是否提示',  dataIndex: 'showtype', width: 80,sortable:true,renderer:function(value){
			        	if(value=='1'){
			        		return "不提示";
			        	}else if(value=='2'){
			        		return "提示";
			        	}else{
			        		return value;
			        	}
		        	},
		        },
		        {header: '升级类型',  dataIndex: 'upgradetype', width: 80,sortable:true,renderer:function(value){
			        	if(value=='1'){
			        		return "可选升级";
			        	}else if(value=='2'){
			        		return "强制升级";
			        	}else{
			        		return value;
			        	}
		        	},
		        },
		        {header: '创建人',  dataIndex: 'createby', width: 80,sortable:true },
		        {header: '创建时间',  dataIndex: 'createtime', width: 160,sortable:true,renderer:function(value){
		        	if(value != null){
		        		return Ext.util.Format.date(new Date(value),'Y-m-d H:i:s');
		        	}else{
		        		return '';
		        	}
	        	}
	        },
		        {header: '修改人',  dataIndex: 'updateby', width: 80,sortable:true },
		        {header: '修改时间',  dataIndex: 'updatetime', width: 160,sortable:true,renderer:function(value){
		        	if(value != null){
		        		var time = Ext.util.Format.date(new Date(value),'Y-m-d H:i:s')
		        		if(time=='1970-01-01 00:00:00'){
		        			time = '';
		        		}
		        		return time;
		        	}else{
		        		return '';
		        	}
	        	}}
		     ],
	selModel: {
	listeners: {
			select: function(rowModel, record, index, eOpts){
				eastPanel.getStore().load({params: {version: record.data.version}});
			}
		},	
		mode: 'MULTI'
	},
	store: Ext.create('Ext.data.JsonStore', {
		autoLoad: true,
		storeId: 'centerStore',
		pageSize: 20,
	    fields :['id', 'version','serialno','memo','pkgurl','clientid','showtype','upgradetype',
	             'createby','createtime','updateby','updatetime'],
	    proxy: {
	        type: 'ajax',
	        url: path + '/clotho/upgrade/list.do',
	        reader: {
	            type: 'json',
	            totalProperty: "result",
	            root: 'rows'
	        }
	    }
	}),
	tbar: [
	       	{
	       		xtype: 'button',
	       		text: '增加',
	       		handler: function(){
		       		Ext.getCmp('upgradeForm').getForm().reset();
	   				Ext.getCmp('pkgUploadForm').getForm().reset();
	   				upgradePop.setTitle('增加');
		       		upgradePop.show();
	       		}
	       	},'-',{
	       		xtype: 'button',
	       		text: '编辑',
	       		handler: function(){
	       			
	       			var models = centerPanel.getSelectionModel().getSelection();
	       			if(models.length <= 0){
	       				Ext.Msg.alert('系统提示', '请选择要编辑的数据');
	       				return;
	       			}
	       			
	       			upgradePop.setTitle('编辑');
	       			upgradePop.show();
	       			
	       			console.log(models[0].data);
	       			Ext.getCmp('upgradeForm').loadRecord(models[0]);
	       			if(models[0].data.pkgurl){
	       				Ext.getCmp('pkgurl').setValue(models[0].data.pkgurl);
	       			}
	       		}
	       	},'-',{
	       		xtype: 'button',
	       		text: '删除',
	       		handler: function(){
	       			var models = centerPanel.getSelectionModel().getSelection();
	       			if(models.length <= 0){
	       				Ext.Msg.alert('系统提示', '请选择要删除的数据');
	       				return;
	       			}
	       			Ext.Msg.confirm('系统提示', '您确认要删除吗?', function(option){
	       				if('yes' === option){
	       					deleteUpgrades(models);
	       				}
	       			});
	       		}
	       	}
	      ],
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
       			if(models.length <= 0){
       				Ext.Msg.alert('系统提示', '请选择要编辑的数据');
       				return;
       			}
       			
       			upgradePop.setTitle('编辑');
       			upgradePop.show();
       			
       			console.log(models[0].data);
       			Ext.getCmp('upgradeForm').loadRecord(models[0]);
       			if(models[0].data.pkgurl){
       				Ext.getCmp('pkgurl').setValue(models[0].data.pkgurl);
       			}
            }
        } 
    }
});

function deleteUpgrades(records){
	Ext.Ajax.request({
		url: path + '/clotho/upgrade/delVersion.do?id='+records[0].get('id'),
		scope: this,
		async: true,
		success: function(response, options){
			Ext.Msg.alert("系统提示", "删除成功");
			centerPanel.getStore().reload();
		},
		failure: function(form, action){
			Ext.Msg.alert('系统提示', action.result.msg);
		}
	});
}

function deletePlans(records){
	Ext.Ajax.request({
		url: path + '/clotho/upgrade/delPlan.do?id='+records[0].get('id'),
		scope: this,
		async: true,
		success: function(response, options){
			Ext.Msg.alert("系统提示", "删除成功");
			eastPanel.getStore().reload();
		},
		failure: function(form, action){
			Ext.Msg.alert('系统提示', action.result.msg);
		}
	});
}

var eastPanel = Ext.create('Ext.grid.Panel', {
	region: 'east',
	title: '计划列表',
	width: 120,
	split: true,
	collapsible: true,
	columns: [
	          	{header: 'ID', align: 'center', width: 50, dataIndex: 'id'},
	          	{header: 'originid', align: 'center', width: 100, dataIndex: 'originid',hidden:true},
	          	{header: 'FROM版本号', dataIndex: 'version', width: 100,renderer:function(value){
			        	if(value!=''){
			        		return value;
			        	}else{
			        		return "全版本";
			        	}
		        	},
		        },
		        {header: '版本描述',  dataIndex: 'memo', width: 300,sortable:true },
		        {header: '客户端类型',  dataIndex: 'clientid', width: 85,sortable:true,renderer:function(value){
			        	if(value=='1'){
			        		return "Android";
			        	}else if(value=='2'){
			        		return "IOS";
			        	}else if(value=='3'){
			        		return 'AndroidPad';
			        	}else{
			        		return value;
			        	}
		        	},
		        },
		        {header: '是否提示',  dataIndex: 'showtype', width: 80,sortable:true,renderer:function(value){
			        	if(value=='1'){
			        		return "不提示";
			        	}else if(value=='2'){
			        		return "提示";
			        	}else{
			        		return value;
			        	}
		        	},
		        },
		        {header: '升级类型',  dataIndex: 'upgradetype', width: 80,sortable:true,renderer:function(value){
			        	if(value=='1'){
			        		return "可选升级";
			        	}else if(value=='2'){
			        		return "强制升级";
			        	}else{
			        		return value;
			        	}
		        	},
		        },
		        {header: '创建人',  dataIndex: 'createby', width: 80,sortable:true },
		        {header: '创建时间',  dataIndex: 'createtime', width: 160,sortable:true,renderer:function(value){
		        	if(value != null){
		        		return Ext.util.Format.date(new Date(value),'Y-m-d H:i:s');
		        	}else{
		        		return '';
		        	}
	        	}
	        },
		        {header: '修改人',  dataIndex: 'updateby', width: 80,sortable:true },
		        {header: '修改时间',  dataIndex: 'updatetime', width: 160,sortable:true,renderer:function(value){
		        	if(value != null){
		        		var time = Ext.util.Format.date(new Date(value),'Y-m-d H:i:s')
		        		if(time=='1970-01-01 00:00:00'){
		        			time = '';
		        		}
		        		return time;
		        	}else{
		        		return '';
		        	}
	        	}}
		     ],
	store: Ext.create('Ext.data.JsonStore', {
	    storeId:'eastStore',
	    fields :['id','originid','version','upgradeid','memo','clientid','showtype','upgradetype',
		             'createby','createtime','updateby','updatetime'],
	    proxy: {
	        type: 'ajax',
	        url: path + '/clotho/upgrade/planlist.do',
	        reader: {
	        	totalProperty: 'results',
	            root: 'rows'
	        }
	    },
	    listeners: {
	    	load: function(store, records, isSucc, eOpts){
	    		if(store.getCount() > 0){
	    			eastPanel.setWidth(500);
	    		}else{
	    			eastPanel.setWidth(120);
	    		}
	    	}
	    }
	}),
	tbar: [
			{	//计划列表表头添加按钮
	       		xtype: 'button',
	       		text: '增加',
	       		handler: function(){
	       			var upgrades = centerPanel.getSelectionModel().getSelection();
	       			if(upgrades.length == 0){
	       				Ext.Msg.alert('系统提示', '请选择升级包');
	       				return;
	       			}
	       			_upgradeid=upgrades[0].data.serialno;
	       			Ext.getCmp('planForm').getForm().reset();
	       			Ext.getCmp('planWin').setTitle('添加');
	       			Ext.getCmp('planWin').show();
	       		}
	       	},'-',{//计划列表表头编辑按钮
	       		xtype: 'button',
	       		text: '编辑',
	       		handler: function(){
	       			var plans = eastPanel.getSelectionModel().getSelection();
       				if(plans.length == 0){
       					Ext.Msg.alert('系统提示', '请选择要编辑的计划。'); return;
       				}
       				Ext.getCmp('planForm').getForm().reset();
       				Ext.getCmp('planWin').setTitle('编辑');
       				Ext.getCmp('planWin').show();
       				
       				//初始化值
       				var fields = Ext.getCmp('planForm').getForm().getFields(),
       					plan = plans[0];
       				Ext.getCmp('planForm').getForm().loadRecord(plan);
       				
	       		}
	       	},'-',{
	       		xtype: 'button',
	       		text: '删除',
	       		handler: function(){
	       			var plans = eastPanel.getSelectionModel().getSelection();
       				if(plans.length == 0){
       					Ext.Msg.alert('系统提示', '请选择要删除的计划。'); return;
       				}
	       			Ext.Msg.confirm('系统提示', '您确认要删除吗?', function(option){
	       				if('yes' === option){
	       					deletePlans(plans);
	       				}
	       			});
	       		}
	       	}
	],
	bbar: Ext.create('Ext.toolbar.Paging', {
        store: Ext.data.StoreManager.get('eastStore'),
        displayInfo: true,
        displayMsg: '第{0}-{1}条，共{2}条',
        emptyMsg: "没有数据",
        beforePageText: '第',
        afterPageText: '页，共 {0} 页'
    }),
});

//domReady
Ext.onReady(function(){
	Ext.create('Ext.container.Viewport', {
		layout: 'border',
		items:[centerPanel,eastPanel]
	});
});