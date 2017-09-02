 (function ()
{
    'use strict';
 angular
    .module('hackathonRoom')
    .directive('fileModel',  fileModel)
    .directive('errSrc',  errSrc)
    
    function fileModel($parse) {
        return {
            restrict: 'A',
            link: function(scope, element, attrs) {
                var model = $parse(attrs.fileModel);
                var modelSetter = model.assign;

                element.bind('change', function(){
                    scope.$apply(function(){
                        modelSetter(scope, element[0].files[0]);
                    });
                });
            }
        };
    }
    function errSrc() {
        return {
            link: function(scope, element, attrs) {
              element.bind('error', function() {
                if (attrs.src != attrs.errSrc) {
                  attrs.$set('src', attrs.errSrc);
                }
              });
            }
        }
    }
 })();
