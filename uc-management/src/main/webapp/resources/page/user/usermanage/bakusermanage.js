var UserTableManaged = function () {

    var userTable = function (data) {

        var table = $('#userdata');

        alert(JSON.stringify(data));
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
                    "previous": "前一页",
                    "next": "后一页"
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
            }],
            "order": [
                [1, "asc"]
            ],
            "columns": [
                { "data": "id", "sWidth": "33%"},
                { "data": "userid", "sWidth": "33%"},
                { "data": "password", "sWidth": "33%"}
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
            userTable(data);
        }

    };

}();

