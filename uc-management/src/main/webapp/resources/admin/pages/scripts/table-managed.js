var TableManaged = function () {

    var initTable1 = function () {

        var table = $('#sample_1');

        // begin first table
        table.dataTable({
            "columns": [
                {
                    data: 'name'
            }, {
                    data: 'position'
            }, {
                    data: 'salary'
            }, {
                    data: 'start_date'
            }, {
                    data: 'office'
            }, {
                    data: 'extn'
            }],
            "lengthMenu": [
                [5, 15, 20, -1],
                [5, 15, 20, "All"] // change per page values here
            ],
            // set the initial value
            "pageLength": 5,            
            "pagingType": "bootstrap_full_number",
            "language": {
                "lengthMenu": " 每页记录条数: _MENU_ ",
                "paginate": {
                    "previous":"前一页",
                    "next": "后一页",
                    "last": "末页",
                    "first": "首页"
                }
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
            ], // set first column as a default sort by asc

            data: [
                {
                    "name":       "Tiger Nixon",
                    "position":   "System Architect",
                    "salary":     "$3,120",
                    "start_date": "2011/04/25",
                    "office":     "Edinburgh",
                    "extn":       "5421"
                },
                {
                    "name":       "Tiger Nixon",
                    "position":   "System Architect",
                    "salary":     "$3,120",
                    "start_date": "2011/04/25",
                    "office":     "Edinburgh",
                    "extn":       "765567"
                },
                {
                    "name":       "Tiger Nixon",
                    "position":   "System Architect",
                    "salary":     "$3,120",
                    "start_date": "2011/04/25",
                    "office":     "Edinburgh",
                    "extn":       "5434221"
                },
                {
                    "name":       "Tiger Nixon",
                    "position":   "System Architect",
                    "salary":     "$3,120",
                    "start_date": "2011/04/25",
                    "office":     "Edinburgh",
                    "extn":       "545436521"
                },
                {
                    "name":       "Tiger Nixon",
                    "position":   "System Architect",
                    "salary":     "$3,120",
                    "start_date": "2011/04/25",
                    "office":     "Edinburgh",
                    "extn":       "321315"
                },
                {
                    "name":       "Tiger Nixon",
                    "position":   "System Architect",
                    "salary":     "$3,120",
                    "start_date": "2011/04/25",
                    "office":     "Edinburgh",
                    "extn":       "5141"
                },
                {
                    "name":       "Garrett Winters",
                    "position":   "Director",
                    "salary":     "$5,300",
                    "start_date": "2011/07/25",
                    "office":     "Edinburgh",
                    "extn":       "8422"
                }
            ]
        });

        var tableWrapper = jQuery('#sample_1_wrapper');

        table.find('.group-checkable').change(function () {
            var set = jQuery(this).attr("data-set");
            var checked = jQuery(this).is(":checked");
            jQuery(set).each(function () {
                if (checked) {
                    $(this).attr("checked", true);
                    $(this).parents('tr').addClass("active");
                } else {
                    $(this).attr("checked", false);
                    $(this).parents('tr').removeClass("active");
                }
            });
            jQuery.uniform.update(set);
        });

        table.on('change', 'tbody tr .checkboxes', function () {
            $(this).parents('tr').toggleClass("active");
        });

        tableWrapper.find('.dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
    }

    var initTable2 = function () {

        var table = $('#sample_2');

        table.dataTable({
            "lengthMenu": [
                [5, 15, 20, -1],
                [5, 15, 20, "All"] // change per page values here
            ],
            // set the initial value
            "pageLength": 5,
            "language": {
                "lengthMenu": " _MENU_ records",
                "paging": {
                    "previous": "Prev",
                    "next": "Next"
                }
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
            ] // set first column as a default sort by asc
        });

        var tableWrapper = jQuery('#sample_2_wrapper');

        table.find('.group-checkable').change(function () {
            var set = jQuery(this).attr("data-set");
            var checked = jQuery(this).is(":checked");
            jQuery(set).each(function () {
                if (checked) {
                    $(this).attr("checked", true);
                } else {
                    $(this).attr("checked", false);
                }
            });
            jQuery.uniform.update(set);
        });

        tableWrapper.find('.dataTables_length select').select2(); // initialize select2 dropdown
    }

    var initTable3 = function () {

        var table = $('#sample_3');

        // begin: third table
        table.dataTable({
            "lengthMenu": [
                [5, 15, 20, -1],
                [5, 15, 20, "All"] // change per page values here
            ],
            // set the initial value
            "pageLength": 5,
            "language": {
                "lengthMenu": " _MENU_ records"
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
            ] // set first column as a default sort by asc
        });

        var tableWrapper = jQuery('#sample_3_wrapper');

        table.find('.group-checkable').change(function () {
            var set = jQuery(this).attr("data-set");
            var checked = jQuery(this).is(":checked");
            jQuery(set).each(function () {
                if (checked) {
                    $(this).attr("checked", true);
                } else {
                    $(this).attr("checked", false);
                }
            });
            jQuery.uniform.update(set);
        });

        tableWrapper.find('.dataTables_length select').select2(); // initialize select2 dropdown
    }

    return {

        //main function to initiate the module
        init: function () {
            if (!jQuery().dataTable) {
                return;
            }

            initTable1();
            initTable2();
            initTable3();
        }

    };

}();