job('Prueba docker') {
  description('Aplicacion hijo de Job2')
  scm {
    git('https://github.com/IsraelGalvez/jenkins.job.parametrizado.git', 'main') { node ->
      node / gitConfigName('IsraelGalvez')
      node / gitConfigEmail('galvez.rli.51@gmail.com')
        }
    }
  triggers {
    scm('H/7 * * * *')
  }
  wrappers {
    nodejs('nodejs')
  }
  steps {
    dockerBuildAndPublish {
      repositoryName('joseexample/nodejsapp3')
      tag('${GIT_REVISION,length=7}')
      registryCredentials('docker-hub')
      forcePull(false)
      createFingerprints(false)
      skipDecorate()
    }
  }
  publishers {
    slackNotifier {
      notifyAborted(true)
      notifyEveryFailure(true)
      notifyNotBuilt(false)
      notifyUnstable(false)
      notifyBackToNormal(true)
      notifySuccess(true)
      notifyRepeatedFailure(false)
      startNotification(false)
      includeTestSummary(false)
      includeCustomMessage(false)
      customMessage(null)
      sendAs(null)
      commitInfoChoice('NONE')
      teamDomain(null)
      authToken(null)
    }
  }
}
