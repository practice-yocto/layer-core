# Short description of the package
DESCRIPTION = "Example Hello Application with systemd service"

# License declaration and checksum for license file validation
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=dcc8c783234a039fc6290e37b7341e86"

# Source code location (Git repository)
SRC_URI = "git://github.com/practice-yocto/hello-app.git;protocol=https;branch=main"

# Always fetch the latest commit from the branch
SRCREV = "${AUTOREV}"

# Package version: includes git revision for reproducibility
PV = "1.0+git${SRCPV}"

# Directory where Yocto places the fetched source code
# (git fetcher unpacks into WORKDIR/git)
S = "${WORKDIR}/git"

# Build directory for out-of-tree builds
# This works together with the Makefile's O= variable
B ?= "${WORKDIR}/build"

# Compile step
# -C ${S} : run make inside the source directory where the Makefile exists
# O=${B}  : place all build artifacts in the build directory
# LDFLAGS : ensure Yocto's linker flags (e.g., GNU_HASH) are applied
do_compile() {
    oe_runmake -C ${S} O=${B} LDFLAGS="${LDFLAGS}"
}

# Install step
# Installs the built binary into the target root filesystem under /usr/bin
do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${B}/hello-app ${D}${bindir}/
}
