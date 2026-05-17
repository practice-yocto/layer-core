# This recipe builds an executable that links against libhello.so
DESCRIPTION = "This recipe builds an executable that uses libhello.so"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=dcc8c783234a039fc6290e37b7341e86"

# Fetch the application source from the Git repository
SRC_URI = "git://github.com/practice-yocto/use-hello-lib.git;protocol=https;branch=main"
SRCREV = "${AUTOREV}"

# Set package version and define source/build directories
PV = "1.0+git${SRCPV}"
S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

# Enable pkg-config support so the build system can locate hello-lib
inherit pkgconfig

# Ensure hello-lib is available during build (headers, .pc file, library)
DEPENDS = "hello-lib"

# Compile the application using the Makefile in the source tree
do_compile() {
    oe_runmake -C ${S} O=${B}
}

# Install the built executable into the target filesystem
do_install() {
    install -d ${D}/${bindir}
    install -m 0755 use-hello-lib ${D}/${bindir}
}

# Package the installed executable
FILES:${PN} += "${bindir}/use-hello-lib"
