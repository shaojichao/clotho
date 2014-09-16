var DeviceTableManaged = function () {

    var deviceTable = function (data) {

        var table = $('#devicedata');

//        alert(JSON.stringify(data));
        table.dataTable({
            "lengthMenu": [
                [5, 15, 20, -1],
                [5, 15, 20, "All"] // change per page values here
            ],
            // set the initial value
            "pageLength": 5,
            "language": {
                "lengthMenu": "每页 _MENU_ 条记录",
                "paging": {
                    "first" : "首页",
                    "previous": "前一页",
                    "next": "后一页",
                    "last" : "尾页"
                },
                "search":"在当前表格中查询：",
                info:"共 _TOTAL_ 条记录，当前显示 _START_ 到 _END_ "
            },
            "columnDefs": [{  // set default column settings
                'orderable': false,
                'targets': [0]
            }, {
                "searchable": false,
                "targets": [0]
            },{
                "targets": [5],
                "data": "deviceid",
                "render": function(data, type, full) {return "<a href='#modify' name='editDevice' class='btn default btn-xs black' data-toggle='modal'  onclick='updateDevice("+data+")' ><i class='fa fa-edit'></i>更新</a>  <a href='#delete' name='deleteDevice' class='btn default btn-xs black' data-toggle='modal'  onclick='deleteDevice("+data+")' ><i class='fa fa-trash-o'></i>删除</a>";}
            }],
//            tableTools: {
//                sRowSelect: "os",
//                aButtons: [
//                    { sExtends: "editor_create", editor: editor ,"sButtonText": "新建货币"},
//                    { sExtends: "editor_edit",   editor: editor ,"sButtonText": "修改货币信息"}
//                ]
//            },
            "order": [
                [0, "desc"]
            ],
            "columns": [
                { "data": "deviceid"},
                { "data": "devicesn"},
                { "data": "devicetype"},
                { "data": "location"},
                { "data": "produceddate"}
            ], // set first column as a default sort by asc
            "data":data
        });

//         var tableWrapper = jQuery('#userdata_wrapper');
//
//        table.find('.group-checkable').change(function () {
//            var set = jQuery(this).attr("data-set");
//            var checked = jQuery(this).is(":checked");
//            jQuery(set).each(function () {
//                if (checked) {
//                    $(this).attr("checked", true);
//                } else {
//                    $(this).attr("checked", false);
//                }
//            });
//            jQuery.uniform.update(set);
//        });
//
//        tableWrapper.find('.dataTables_length select').select2(); // initialize select2 dropdown
    }

    return {

        //main function to initiate the module
        init: function (data) {
            if (!jQuery().dataTable) {
                return;
            }
            deviceTable(data);
        }

    };

}();

