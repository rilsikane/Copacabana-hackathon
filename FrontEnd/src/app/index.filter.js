(function ()
{
    'use strict';

    angular
        .module('hackathonRoom')
        .filter('thaiDatetimeShort',  thaiDatetimeShort)
        .filter('thaiDateShort',  thaiDateShort)
        .filter('importStatus',  importStatus)
        .filter('orderStatus',orderStatus)
        .filter('salary',salary)
        .filter('role',role)
        .filter('cut',cut)

        function thaiDatetimeShort($filter) {
        	return function(input){
		       if(!input){return "";}
		       var _date =$filter('date')(new Date(input), "dd/MM/yyyy");
		       var _time =$filter('date')(new Date(input), "HH:mm");
		       // console.log(_date);
		       if(_date){
		           var tmp = _date.split("/");
		           var thaiYear = parseInt(tmp[2])+543;
		           return tmp[0]+"/"+tmp[1]+"/"+thaiYear+" "+_time;
		       }else{
		           return "";
		       }
		   }
		}
		function thaiDateShort($filter) {
        	return function(input){
		       if(!input){return "";}
		       var _date =$filter('date')(new Date(input), "dd/MM/yyyy");
		       var _time =$filter('date')(new Date(input), "HH:mm");
		       // console.log(_date);
		       if(_date){
		           var tmp = _date.split("/");
		           var thaiYear = parseInt(tmp[2])+543;
		           return tmp[0]+"/"+tmp[1]+"/"+thaiYear+" ";
		       }else{
		           return "";
		       }
		   }
		}
		function importStatus($filter) {
        	return function(input){
		       var result;
	            var valCase = $filter('uppercase')(input);
	            switch(valCase){
	                case 'REJECTED':
	                 result = '<span><i class="icon-cancel red-500-fg"></i> ยกเลิก<span>'
	                break;
	                case 'COMPLETE':
	                 result = '<span><i class="icon-checkbox-marked-circle green-500-fg"></i> สำเร็จ<span>'
	                break;
	                default:
	                  result = '-'
		            }
	            return result;
		   }
		}
		function orderStatus($filter) {
        	return function(input){
		       var result;
	            var valCase = $filter('uppercase')(input);
	            switch(valCase){
	                case 'OPEN':
	                 result = '<span><i class="icon-checkbox-blank-circle yellow-500-fg"></i> เปิด<span>'
	                break;
	                case 'CANCEL':
	                 result = '<span><i class="icon-cancel red-500-fg"></i> ยกเลิก<span>'
	                break;
	                case 'COMPLETE':
	                 result = '<span><i class="icon-checkbox-marked-circle green-500-fg"></i> ปิด<span>'
	                break;
	                default:
	                  result = '-'
		            }
	            return result;
		   }
		}
		function salary(){
			 return function (input) {
	            if (input == null) { return 0; }
	            return parseFloat(input).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	        }
		}
		function role(){
			 return function (input) {
	            if (input == null) { return "-"; }
	            return input=="2" ? "ผู้ดูแลระบบ":"Sale";
	        }
		}
		function cut() {
	        return function (value, wordwise, max, tail) {
	            if (!value) return '';

	            max = parseInt(max, 10);
	            if (!max) return value;
	            if (value.length <= max) return value;

	            value = value.substr(0, max);
	            if (wordwise) {
	                var lastspace = value.lastIndexOf(' ');
	                if (lastspace !== -1) {
	                  //Also remove . and , so its gives a cleaner result.
	                  if (value.charAt(lastspace-1) === '.' || value.charAt(lastspace-1) === ',') {
	                    lastspace = lastspace - 1;
	                  }
	                  value = value.substr(0, lastspace);
	                }
	            }

	            return value + (tail || ' …');
	        };
    	}	

 })();