# Use the externalsrc class to build directly from a local source tree
inherit externalsrc

# Path to the external source directory.
# This allows Yocto to skip fetching/unpacking and instead use the
# developer’s working copy during the build.
EXTERNALSRC = "${COREBASE}/../external/use-hello-lib"

# Separate build directory used when performing out-of-tree builds.
# Yocto will run the build steps inside this directory while still
# using the external source directory as the source tree.
EXTERNALSRC_BUILD = "${WORKDIR}/build"

# Ensure version is always higher than git-based builds
PV .= "+extsrc"
