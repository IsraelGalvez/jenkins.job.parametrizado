job('Aplicacion hijo') {
  description('Aplicacion hijo de Job2')
  scm {
    git('https://github.com/IsraelGalvez/jenkins.job.parametrizado.git', 'master') { node ->
      node / gitConfigName('buhm0')
      node / gitConfigEmail('corvus164@gmail.com')
        }
    }
  steps {
    dockerBuildAndPublish {
      repositoryName('macloujulian/nodejsapp')
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
