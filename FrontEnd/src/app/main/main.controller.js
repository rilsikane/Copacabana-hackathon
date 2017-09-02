(function() {
  'use strict';

  angular
    .module('hackathonRoom')
    .controller('MainController', MainController)
    .controller('ModalInstanceCtrl', ModalInstanceCtrl)
    .controller('SignUpModalInstanceCtrl',SignUpModalInstanceCtrl);

  /** @ngInject */
  function MainController($uibModal,$scope) {

      $scope.login = function(){
         var modalInstance = $uibModal.open({
          animation: true,
          templateUrl: 'myModalContent.html',
          controller: 'ModalInstanceCtrl',
          size: 'lg'
        });

      
    }
    $scope.init = function(){
      $scope.map = { center: { latitude: 13.821485, longitude: 100.307497 }, zoom: 15 };
    }

    $scope.init();
    
  }
   function ModalInstanceCtrl($scope,facebookService,$uibModalInstance,$uibModal){
      $scope.facebookLogin = function(){
         facebookService.login().then(function(result){
            console.log("FB-Rsult"+result.authResponse.userID);
             $uibModalInstance.dismiss('cancel');
             var modalInstance = $uibModal.open({
              animation: true,
              templateUrl: 'signUpModal.html',
              controller: 'SignUpModalInstanceCtrl',
              size: 'lg'
            });

         });
      }
   }
   function SignUpModalInstanceCtrl($scope,facebookService,$uibModalInstance,$uibModal,ApiService,msgDialog){
    $scope.signUpForm = {};
     facebookService.api('/me',{fields: 'name,email'}).then(function (response){
        console.log("FB-Rsult"+response);
        $scope.signUpForm.userName = response.email;
        var names = response.name.split(" ");
        $scope.signUpForm.firstName = names[0];
        $scope.signUpForm.lastName = names[1];
        $scope.signUpForm.facebookToken = response.id;
     });
      $scope.signUp = function(){
         ApiService.postService($scope.signUpForm,"user.register.service").then(function(result){
            console.log(result);
            if(result.msg=="100"){
               swal(
                  "สมัครสมาชิกเรียบร้อย",
                  "คุณได้สมัครสมาชิกเรียบร้อย",
                  'success'
                ).then(function(){
                  $uibModalInstance.dismiss('cancel');
                })
            }else if (result.msg=="101"){
               swal(
                  "เกิดข้อผิดพลาด",
                  "มีข้อมูล User Name นี้อยุ่ในระบบแล้ว",
                  'error'
                ).then(function(){
                  $uibModalInstance.dismiss('cancel');
                })
            }else if (result.msg=="200"){
               swal(
                  "เกิดข้อผิดพลาด",
                  "ไม่สามารถติดต่อ Server ได้",
                  'error'
                ).then(function(){
                  $uibModalInstance.dismiss('cancel');
                })
            }
            
          })
      }
   }
})();
