var menuPop = Ext.create('Ext.window.Window', {
	id: 'menuWin',
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
            	id: 'menuForm',
            	width: 460,
            	height: 250,
                url: path + '/menu/saveMenu.do',
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
            		        fieldLabel: '名称',
            		        name: 'text',
            		        allowBlank: false,
            		        maxLength: 40,
            		        maxLengthText: '不能超过20个字符'
                		},{
            		        fieldLabel: 'URL',
            		        name: 'url'
                		},{
                			xtype: 'combo',
							name: 'parentID',
							fieldLabel: '父菜单',
							store: Ext.create('Ext.data.JsonStore', {
            	       			storeId: 'rootMenu',
            	       			autoLoad: true,
            	       			fields: ['id', 'text'],
            	       			proxy: {
            	       				type: 'ajax',
            	       				url: path + '/menu/rootlist.do',
            		       			reader: {
            		       				totalProperty: 'results',
            			       			root: 'rows'
            		       			}
            	       			}
            	       		}),
            	       		displayField: 'text',
					   	    valueField: 'id',
                		},{
                			fieldLabel: '状态',
                	        xtype: 'fieldcontainer',
                	        defaultType: 'radiofield',
            	            layout: 'hbox',
            	            items: [
            	                {
            	                    boxLabel: '有效',
            	                    name: 'status',
            	                    inputValue: 'ACTIVE',
            	                    checked: true
            	                }, {
            	                    boxLabel: '无效',
            	                    name: 'status',
            	                    id: 'status_inactive',
            	                    inputValue: 'INACTIVE'            	                    
            	                }
            	            ]
                		},{
                			fieldLabel: '叶节点',
                	        xtype: 'fieldcontainer',
                	        defaultType: 'radiofield',
            	            layout: 'hbox',
            	            items: [
            	                {
            	                    boxLabel: '是',
            	                    name: 'leaf',
            	                    inputValue: true,
            	                    checked: true
            	                }, {
            	                    boxLabel: '否',
            	                    name: 'leaf',
            	                    id: 'leaf_no',
            	                    inputValue: false
            	                }
            	            ]
                		}]
            }),
    ],
    buttons: [
        {
        	text: '保存',
        	handler: function(){
        		
        		if(!Ext.getCmp('menuForm').getForm().isValid()){
        			return;
        		}
        		
        		Ext.getCmp('menuForm').submit({
        			waitTitle: '系统提示',
        			waitMsg: '保存中......',
        			success: function(form, action){
        				Ext.getCmp('menuWin').hide();
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


var centerPanel = Ext.create('Ext.grid.Panel', {
	region: 'center',
	title: '菜单列表',
    columns: [
		        {header: 'ID',  dataIndex: 'id', width: 60,sortable:true },
		        {header: '名称', dataIndex: 'text', width: 150},
		        {header: '父菜单Id', dataIndex: 'parentID', width: 80},
		        {header: '父菜单名称', dataIndex: 'parentName', width: 150},
		        {header: '跳转',  dataIndex: 'url', width: 300,sortable:true },
		        {header: '叶节点',  dataIndex: 'leaf', width: 80,sortable:true,renderer:function(value){
			        	if(value){
			        		return "是";
			        	}else{
			        		return '否';
			        	}
		        	},
		        },
		        {header: '状态',  dataIndex: 'status', width: 50,sortable:true,renderer:function(value){
			        	if(value=='ACTIVE'){
			        		return "有效";
			        	}else{
			        		return '无效';
			        	}
		        	},
		        },
		        {header: '创建人',  dataIndex: 'createdBy', width: 80,sortable:true },
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
	    fields :['id', 'text','parentID','parentName','url','leaf','status','createTime','createdBy'],
	    proxy: {
	        type: 'ajax',
	        url: path + '/menu/allList.do',
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
		       		Ext.getCmp('menuForm').getForm().reset();
		       		menuPop.setTitle('增加');
		       		menuPop.show();
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
	       			if(!models[0].data.leaf){
	       				Ext.getCmp('leaf_no').setValue(true);
	       			}
	       			if(models[0].data.status!='ACTIVE'){
	       				Ext.getCmp('status_inactive').setValue(true);
	       			}
	       			menuPop.setTitle('编辑');
	       			menuPop.show();
	       			
	       			Ext.getCmp('menuForm').loadRecord(models[0]);
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
       			
       			menuPop.setTitle('编辑');
       			menuPop.show();
       			
       			Ext.getCmp('menuForm').loadRecord(models[0]);
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