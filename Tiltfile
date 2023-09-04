# -*- mode: Python -*-

gradlew = './gradlew'
if os.name == 'nt':
  gradlew = 'gradlew'

imageNameEnv = '$EXPECTED_REF'
if os.name == 'nt':
  imageNameEnv = '%EXPECTED_REF%'

# Build
custom_build(
    # Name of the container image
    ref = 'catalog',
    # Command to build the container image
    command = gradlew + ' bootBuildImage --imageName ' + imageNameEnv,
    # Files to watch that trigger a new build
    deps = ['build.gradle', 'src'],
)

# Deploy
k8s_yaml(['deployment/K8s/Platform/postgresql.yml', 'deployment/K8s/Application/catalog.yml'])


# Manage
k8s_resource('catalog', port_forwards=['9001'])