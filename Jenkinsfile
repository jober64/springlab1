@Library('pipeline-library') _
node('maven3') {

  def serviceName = 'fake-balances-service'
  def gitURL      = 'https://github.sebank.se/s66214/fake-balances.git'

  def envDev      = 'exploration-day'

  def utils = new se.seb.build.Utils()

  stage('Checkout') {
    git url: "${gitURL}"
  }

  def version = utils.getPomVersion()

  stage('Build') {
    sh "mvn -q -s settings.xml -DskipTests package"
  }

  stage('Test and Analysis') {
    parallel (
      'Test': {
        sh 'mvn -s settings.xml test'
      },
      'Static Analysis': {
        echo 'PLACEHOLDER <sonarqube>'
      }
    )
  }

  stage('Build and Deploy DEV') {
    sh "oc project ${envDev}"

    sh "oc start-build ${serviceName} --from-file target/*.jar -n ${envDev} --follow"
    sh "oc tag ${envDev}/${serviceName}:latest ${envDev}/${serviceName}:${version}"

    openshiftDeploy(deploymentConfig: "${serviceName}", namespace: "${envDev}")
    openshiftVerifyDeployment(deploymentConfig: "${serviceName}", namespace: "${envDev}")
  }

}
