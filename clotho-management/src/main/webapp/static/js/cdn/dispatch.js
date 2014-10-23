
var geoPop = Ext.create('Ext.window.Window', {
    id: 'geoWin',
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
            id: 'geoForm',
            width: 460,
            height: 250,
            url: '/cdn-management/goeBasedDispatch/save?adminName='+adminName,
            layout: 'anchor',
            defaults: {
                anchor: '80%',
                labelAlign: 'right',
                labelWidth: 80,
                blankText: '必填项'
            },
            // The fields
            defaultType: 'textfield',
            items: [
                {//隐藏域
                    name: 'id',
                    hidden: true
                },
                {
                    xtype: "combo",
                    name: 'countryId',
                    id: 'country',
                    fieldLabel: '国家',
                    displayField: 'name',
                    valueField: 'id',
                    triggerAction: 'all',
                    queryMode: 'local',
                    selectOnFocus: true,
                    forceSelection: true,
                    allowBlank: false,
                    editable: true,
                    emptyText: '请选择国家',
                    blankText: '请选择国家',
                    store: Ext.create('Ext.data.JsonStore', {
                        storeId: 'countryStore',
                        autoLoad: true,
                        fields: ['id', 'name'],
                        data: [
                            ['0', '未知'],
                            ['CN', '中国']
                        ]
                    }),
                    listeners: {
                        select: function (combo, record, index) {
                            try {
                                var province_model = Ext.getCmp('province');
                                var district_model = Ext.getCmp("district");
                                province_model.reset();
                                district_model.reset();
                                province_model.store.load({url: '/cdn-management/province/jsonList?countryId=' + this.value});
                            }
                            catch (ex) {
                                Ext.MessageBox.alert("错误", "数据加载失败。");
                            }
                        }
                    }
                },
                {
                    xtype: "combo",
                    name: 'provinceId',
                    id: 'province',
                    fieldLabel: '省份',
                    displayField: 'name',
                    valueField: 'id',
                    triggerAction: 'all',
                    queryMode: 'local',
                    selectOnFocus: true,
                    forceSelection: true,
                    allowBlank: false,
                    editable: true,
                    emptyText: '请选择省份',
                    blankText: '请选择省份',
                    store: Ext.create('Ext.data.JsonStore', {
                        storeId: 'provinceStore',
                        autoLoad: true,
                        fields: ['id', 'name'],
                        proxy: {
                            type: 'ajax',
                            url: '/cdn-management/province/jsonList',
                            reader: {
                                totalProperty: 'results',
                                root: 'rows'
                            }
                        }
                    }),
                    listeners: {
                        select: function (combo, record, index) {
                            try {
                                var district_model = Ext.getCmp("district");
                                district_model.clearValue();
                                district_model.store.load({url: '/cdn-management/district/jsonList?provinceId=' + this.value});
                            }
                            catch (ex) {
                                Ext.MessageBox.alert("错误", "数据加载失败。");
                            }
                        }
                    }

                },
                {
                    xtype: "combo",
                    name: 'districtId',
                    id: 'district',
                    fieldLabel: '地区',
                    displayField: 'districtname',
                    valueField: 'id',
                    triggerAction: 'all',
                    queryMode: 'local',
                    selectOnFocus: true,
                    forceSelection: true,
                    allowBlank: false,
                    editable: true,
                    emptyText: '请选择地区',
                    blankText: '请选择地区',
                    store: Ext.create('Ext.data.JsonStore', {
                        storeId: 'districtStore',
                        autoLoad: true,
                        fields: ['id', 'districtname'],
                        proxy: {
                            type: 'ajax',
                            url: '/cdn-management/district/jsonList',
                            reader: {
                                totalProperty: 'results',
                                root: 'rows'
                            }
                        }
                    })
                },
                {
                    xtype: "combo",
                    name: 'ispId',
                    id: 'isp',
                    fieldLabel: '运营商',
                    displayField: 'name',
                    valueField: 'id',
                    triggerAction: 'all',
                    queryMode: 'local',
                    selectOnFocus: true,
                    forceSelection: true,
                    allowBlank: false,
                    editable: true,
                    emptyText: '请选择运营商',
                    blankText: '请选择运营商',
                    store: Ext.create('Ext.data.JsonStore', {
                        storeId: 'ispStore',
                        autoLoad: true,
                        fields: ['id', 'name'],
                        proxy: {
                            type: 'ajax',
                            url: '/cdn-management/isp/jsonList',
                            reader: {
                                totalProperty: 'results',
                                root: 'rows'
                            }
                        }
                    })
                },
                {
                    xtype: "combo",
                    name: 'node_id',
                    id: 'node',
                    fieldLabel: '节点',
                    displayField: 'name',
                    valueField: 'id',
                    triggerAction: 'all',
                    queryMode: 'local',
                    selectOnFocus: true,
                    forceSelection: true,
                    allowBlank: false,
                    editable: true,
                    emptyText: '请选择节点',
                    blankText: '请选择节点',
                    store: Ext.create('Ext.data.JsonStore', {
                        storeId: 'nodeStore',
                        autoLoad: true,
                        fields: ['id', 'name'],
                        proxy: {
                            type: 'ajax',
                            url: '/cdn-management/node/jsonList',
                            reader: {
                                totalProperty: 'results',
                                root: 'rows'
                            }
                        }
                    })
                },
                {
                    xtype: "combo",
                    name: 'grade',
                    id: 'grade',
                    fieldLabel: '等级',
                    displayField: 'name',
                    valueField: 'id',
                    triggerAction: 'all',
                    queryMode: 'local',
                    selectOnFocus: true,
                    forceSelection: true,
                    allowBlank: false,
                    editable: true,
                    emptyText: '请选择等级',
                    blankText: '请选择等级',
                    store: Ext.create('Ext.data.JsonStore', {
                        storeId: 'gradeStore',
                        autoLoad: true,
                        fields: ['id', 'name'],
                        data: [
                            [1, '一级'],
                            [2, '二级'],
                            [99, '其他']
                        ]
                    })
                }
            ]
        })
    ],
    buttons: [
        {
            text: '保存',
            handler: function () {
                Ext.getCmp('geoForm').submit({
                    waitTitle: '系统提示',
                    waitMsg: '保存中......',
                    success: function (form, action) {
                        Ext.getCmp('geoWin').hide();
                        centerPanel.getStore().reload();
                        Ext.Msg.alert('系统提示', '保存成功！');
                    },
                    failure: function (form, action) {
                        Ext.Msg.alert('系统提示', action.result.msg);
                    }
                });
            }
        },
        {
            text: '取消',
            handler: function () {
                geoPop.hide();
            }
        }
    ]
});


var centerPanel = Ext.create('Ext.grid.Panel', {
    region: 'center',
    title: 'GSLB调度策略列表',
    columns: [
        {header: 'ID', dataIndex: 'id', width: 80},
        {header: '国家', dataIndex: 'countryName', width: 80},
        {header: '省份', dataIndex: 'provinceName', width: 80},
        {header: '地区', dataIndex: 'districtName', width: 80},
        {header: '运营商', dataIndex: 'ispName', width: 150},
        {header: '节点', dataIndex: 'nodeName', width: 80, sortable: true },
        {header: '等级', dataIndex: 'grade', width: 85, sortable: true, renderer: function (value) {
            if (value == '1') {
                return "<span style ='color:red'>一级</span>";
            } else if (value == '2') {
                return "<span style ='color:green'>二级</span>";
            } else {
                return "其他";
            }
        }
        }
    ],
    selModel: {
        listeners: {
            select: function (rowModel, record, index, eOpts) {
                eastPanel.getStore().load({params: {node: record.data.node}});
            }
        },
        mode: 'MULTI'
    },
    store: Ext.create('Ext.data.JsonStore', {
        autoLoad: true,
        storeId: 'centerStore',
        pageSize: 20,
        fields: ['id', 'countryId', 'countryName', 'provinceId', 'provinceName', 'districtId', 'districtName', 'ispId', 'ispName', 'node_id', 'nodeName', 'grade'],
        proxy: {
            type: 'ajax',
            url: '/cdn-management/goeBasedDispatch/list',
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
            handler: function () {
                Ext.getCmp('geoForm').getForm().reset();
                geoPop.setTitle('增加');
                geoPop.show();
            }
        },
        '-',
        {
            xtype: 'button',
            text: '编辑',
            handler: function () {

                var models = centerPanel.getSelectionModel().getSelection();
                if (models.length <= 0) {
                    Ext.Msg.alert('系统提示', '请选择要编辑的数据');
                    return;
                }

                geoPop.setTitle('编辑');
                geoPop.show();

                Ext.getCmp('geoForm').loadRecord(models[0]);

            }
        },
        '-',
        {
            xtype: 'button',
            text: '删除',
            handler: function () {
                var models = centerPanel.getSelectionModel().getSelection();
                if (models.length <= 0) {
                    Ext.Msg.alert('系统提示', '请选择要删除的数据');
                    return;
                }
                Ext.Msg.confirm('系统提示', '您确认要删除吗?', function (option) {
                    if ('yes' === option) {
                        deleteGeo(models);
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
            fn: function () {
                var models = centerPanel.getSelectionModel().getSelection();
                if (models.length <= 0) {
                    Ext.Msg.alert('系统提示', '请选择要编辑的数据');
                    return;
                }
                geoPop.setTitle('编辑');
                geoPop.show();
                Ext.getCmp('geoForm').loadRecord(models[0]);
            }
        }
    }
});

function deleteGeo(records) {
    Ext.Ajax.request({
        url: path + '/cdn-management/goeBasedDispatch/del?id=' + records[0].get('id')+'&adminName='+adminName,
        scope: this,
        async: true,
        success: function (response, options) {
            Ext.Msg.alert("系统提示", "删除成功");
            centerPanel.getStore().reload();
        },
        failure: function (form, action) {
            Ext.Msg.alert('系统提示', action.result.msg);
        }
    });
}


//domReady
Ext.onReady(function () {
    Ext.create('Ext.container.Viewport', {
        layout: 'border',
        items: [centerPanel]
    });
});