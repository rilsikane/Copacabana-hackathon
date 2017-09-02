(function() {
  'use strict';

  angular
    .module('hackathonRoom')
    .factory('ApiService',ApiServices)
    .factory('msgDialog',msgDialog)
    .factory('$localstorage',$localstorage)

  /** @ngInject */
  function ApiServices($window, $http, $q, $rootScope,$timeout) {
    var _timeout = 300000;
    // var endpoint = _api.port ? (_api.host + ':' + _api.port + _api.path) : (_api.host + _api.path);

    var postService = function(parameter,path) {
      var deferred = $q.defer();
          parameter.target=path;
          try{
            $http({
              url: "http://174.138.25.181:8007/gateway/service/post.service",
              headers:{'Content-Type' : 'application/json;charset=utf-8;'
                // ,'token' : $localstorage.get("token")
                // ,'userId': $localstorage.getObject("userData").userId
                // ,'deviceId': $localstorage.get("deviceId")
              },
              method: 'POST',
              data:JSON.stringify(parameter),
              dataType : 'json'
            })
            .success(function(data) {
              //LoadingService.hide();
              deferred.resolve(JSON.parse(data.result));
              //console.log('fetched this stuff from server:', data);

            })
            .error(function(error) {
              swal(
                  'เกิดข้อผิดพลาด',
                  'ไม่สามารถเชื่อมต่อ Service ได้',
                  'error'
                )
            })
          }catch(e){
            deferred.reject({errorCode:"1000",errorMsg:errorServer}) ;
          }

        return deferred.promise;
    };
    var postPrivateService = function(parameter,path) {
          var deferred = $q.defer();
          parameter.target = path;
          try{
            $http({
              url: "http://174.138.25.181:8007/gateway/service/private/post.service",
              headers:{'Content-Type' : 'application/json;charset=utf-8;'
                // ,'token' : $localstorage.get("token")
                // ,'userId': $localstorage.getObject("userData").userId
                // ,'deviceId': $localstorage.get("deviceId")
              },
              method: 'POST',
              data:JSON.stringify(parameter),
              dataType : 'json'
            })
            .success(function(data) {
              //LoadingService.hide();
              deferred.resolve(data);
              //console.log('fetched this stuff from server:', data);

            })
            .error(function(error) {
              swal(
                  'เกิดข้อผิดพลาด',
                  'ไม่สามารถเชื่อมต่อ Service ได้',
                  'error'
                )
            })
          }catch(e){
            deferred.reject({errorCode:"1000",errorMsg:errorServer}) ;
          }

        return deferred.promise;
    };

      var uploadFileToUrl = function(data) {
        // var data = {}; //file object 

        var fd = new FormData();
        fd.append('files', data);
        fd.append('target',"attach.uploadFile.service");

        $http.post("http://localhost:9080/gateway/service/post-file.service", fd, {
            headers: {
              'Content-Type': undefined
            },
            transformRequest: angular.identity,
            responseType: "arraybuffer"
          })
          .then(function(response) {
            var data = response.data;
            var status = response.status;
            console.log(data);
          })
          .catch(function(error) {
            console.log(error.status);

            // handle else calls
          });
      }
    
     
    return {
      postService : postService,
      postPrivateService : postPrivateService,
      uploadFileToUrl:uploadFileToUrl
    };
  }
    function msgDialog()
    {
      return {
        success: function(title, msg,callback) {
          var msgTitle = 'เรียบร้อย'
          if(title){
            msgTitle = title;
          }
          swal(
            msgTitle,
            msg,
            'success'
          ).then(function(){
            if(callback){
              callback();
            }
          })
        },
        error: function(title, msg) {
          var msgTitle = 'เกิดข้อผิดพลาด'
          if(title){
            msgTitle = title;
          }
          swal(
            msgTitle,
            msg,
            'error'
          )
        },
        confirmDelete: function(title,msg,callback){
          var titleMsg = 'ยืนยันการลบข้อมูล'
          if(title){
              titleMsg = title;
          }
          swal({
            title: titleMsg,
            type: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#d33',
            cancelButtonColor: '#3085d6',
            confirmButtonText: 'ลบ',
            cancelButtonText: 'ยกเลิก',
            html:msg
          }).then(function () {
            callback();
          })
        },
        confirm: function(title,msg,callback){
          var titleMsg = 'ยืนยันการทำรายการ'
          if(title){
              titleMsg = title;
          }
          swal({
            title: titleMsg,
            type: 'info',
            showCancelButton: true,
            confirmButtonColor: '#d33',
            cancelButtonColor: '#3085d6',
            confirmButtonText: 'ตกลง',
            cancelButtonText: 'ยกเลิก',
            html:msg
          }).then(function () {
            callback();
          })
        }
      }
    }
    function $localstorage($window) {
      return {
        set: function(key, value) {
          $window.localStorage[key] = value;
        },
        get: function(key, defaultValue) {
          return $window.localStorage[key] || defaultValue;
        },
        setObject: function(key, value) {
          $window.localStorage[key] = JSON.stringify(value);
        },
        getObject: function(key) {
          return JSON.parse($window.localStorage[key] || '{}');
        },
        
      }
    }

})();