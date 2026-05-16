# -----------------------------------------------------------------------------
# Enable externalsrc support
# This allows the recipe to build directly from a local source tree instead of
# fetching and unpacking sources from SRC_URI.
# -----------------------------------------------------------------------------
inherit externalsrc

# -----------------------------------------------------------------------------
# EXTERNALSRC: Path to the local source directory used for compilation.
# EXTERNALSRC_BUILD: Path used for out-of-tree builds (same as EXTERNALSRC here).
# -----------------------------------------------------------------------------
EXTERNALSRC = "${COREBASE}/../external/hello-kmodule"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

# -----------------------------------------------------------------------------
# Disable the original fetch mechanism.
# Since externalsrc is used, the recipe should not fetch sources from Git.
# -----------------------------------------------------------------------------
SRC_URI = ""

# -----------------------------------------------------------------------------
# S: Source directory for the build.
# Must point to the externalsrc path so Yocto uses the local source tree.
# -----------------------------------------------------------------------------
S = "${EXTERNALSRC}"

# Ensure version is always higher than git-based builds
PV .= "+extsrc"
