var exec = require('cordova/exec')

exports.includeInTaskList = function (success, error) {
  exec(success, error, 'TaskList', 'includeInTaskList', [])
}
