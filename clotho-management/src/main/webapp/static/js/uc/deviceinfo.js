var devicePop = Ext.create('Ext.window.Window', {
    id: 'deviceWin',
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
            id: 'deviceForm',
            width: 460,
            height: 250,
            url: path + '/uc_management/devicemanage/saveDevice.do',
            method: 'POST',
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
                    name: 'deviceid',
                    hidden: true
                },
                {
                    fieldLabel: '硬件设备号',
                    name: 'devicesn',
                    allowBlank: false,
                    maxLength: 40,
                    maxLengthText: '不能超过20个字符'
                },
                {
                    fieldLabel: '硬件设备类型',
                    name: 'devicetype',
                    xtype: 'combo',
                    store: Ext.create('Ext.data.ArrayStore', {
                        fields: ['value', 'text'],
                        data: [
                            [1, 'Android'],
                            [2, 'IOS'],
                            [3, 'AndroidPad']
                        ]
                    }),
                    displayField: 'text',
                    valueField: 'value'
                },
                {
                    fieldLabel: '所在区域',
                    name: 'location',
                    xtype: 'combo',
                    store: Ext.create('Ext.data.ArrayStore', {
                        fields: ['value', 'text'],
                        data: [
                            ['CN', '中国'],
                            ['US', '美国']
                        ]
                    }),
                    displayField: 'text',
                    valueField: 'value'
                },
                {
                    fieldLabel: '生产日期',
                    xtype: 'datefield',
                    anchor: '100%',
                    name: 'produceddate',
                    // The value matches the format; will be parsed and displayed using that format.
                    format: 'Y-m-d',
                    maxValue: new Date()  // limited to the current date or prior
                },
                {
                    name: 'updateby',
                    hidden: true
                }
            ]
        })
    ],
    buttons: [
        {
            text: '保存',
            handler: function () {

                if (!Ext.getCmp('deviceForm').getForm().isValid()) {
                    return;
                }
                Ext.getCmp('deviceForm').getForm().findField('updateby').setValue(adminName);
                Ext.getCmp('deviceForm').submit({
                    waitTitle: '系统提示',
                    waitMsg: '保存中......',
                    success: function (form, action) {
                        Ext.getCmp('deviceWin').hide();
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
                devicePop.hide();
            }
        }
    ]
});


var centerPanel = Ext.create('Ext.grid.Panel', {
    region: 'center',
    title: '设备列表',
    columns: [
        {header: '设备序号', dataIndex: 'deviceid', width: 80, sortable: true },
        {header: '设备硬件号', dataIndex: 'devicesn', width: 200},
        {header: '设备类型', dataIndex: 'devicetype', width: 80, renderer: function (value) {
            if (value == '1') {
                return "Android";
            } else if (value == '2') {
                return "IOS";
            } else if (value == '3') {
                return 'AndroidPad';
            } else {
                return value;
            }
        }},
        {header: '所在区域', dataIndex: 'location', width: 80, sortable: true, renderer: function (value) {
            if (value == 'CN') {
                return "中国";
            } else if (value == 'US') {
                return "美国";
            } else {
                return value;
            }
        } },
        {header: '生产日期', dataIndex: 'produceddate', width: 120, sortable: true, renderer: function (value) {
            if (value != null) {
                return Ext.util.Format.date(new Date(value), 'Y-m-d');
            } else {
                return '';
            }
        }
        },
        {header: '修改人', dataIndex: 'updateby', width: 120, sortable: true },
        {header: '修改时间', dataIndex: 'updatetime', width: 160, sortable: true, renderer: function (value) {
            if (value != null) {
                return Ext.util.Format.date(new Date(value), 'Y-m-d H:i:s');
            } else {
                return '';
            }
        }
        }
    ],
    store: Ext.create('Ext.data.JsonStore', {
        autoLoad: true,
        storeId: 'centerStore',
        fields: ['deviceid', 'devicesn', 'devicetype', 'location', 'produceddate', 'updateby', 'updatetime'],
        proxy: {
            type: 'ajax',
            url: path + '/uc_management/devicemanage/list.do',
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
                Ext.getCmp('deviceForm').getForm().reset();
                devicePop.setTitle('增加');
                devicePop.show();
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
                devicePop.setTitle('编辑');
                devicePop.show();

                Ext.getCmp('deviceForm').loadRecord(models[0]);
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
                        deleteDevice(models);
                    }
                });
            }
        },
        '-',
        {
            xtype: 'textfield',
            fieldLabel: '硬件设备号',
            name: 'devicehwid',
            id: 'devicehwid'
        },
        {
            xtype: 'button',
            text: '查询',
            handler: function () {
                centerPanel.getStore().load({url: path + '/uc_management/devicemanage/getbysn.do',
                    params: {devicehwid: Ext.getCmp('devicehwid').getValue()}});
                centerPanel.clearValue();
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
                devicePop.setTitle('编辑');
                devicePop.show();

                Ext.getCmp('deviceForm').loadRecord(models[0]);
            }
        }
    }
});

function deleteDevice(records) {
    Ext.Ajax.request({
        url: path + '/uc_management/devicemanage/delDevice.do',
        scope: this,
        async: true,
        method: "POST",
        params: { deviceid: records[0].get('deviceid'), adminName: adminName},
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