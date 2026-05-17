# This recipe builds and installs the hello-lib shared library
DESCRIPTION = "This recipe makes shared library"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=dcc8c783234a039fc6290e37b7341e86"

# Fetch the library source from the Git repository
SRC_URI = "git://github.com/practice-yocto/hello-lib.git;protocol=https;branch=main"
SRCREV = "${AUTOREV}"

# Set package version and define source/build directories
PV = "1.0+git${SRCPV}"
S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

# Compile the shared library using the Makefile in the source tree
do_compile() {
    oe_runmake -C ${S} O=${B} LDFLAGS="${LDFLAGS}"
}

# Install the shared library, header, and pkg-config file
do_install() {
    # Install shared library and symlinks
    install -d ${D}/${libdir}
    install -m 0755 libhello.so.1.0 ${D}/${libdir}
    ln -s libhello.so.1.0 ${D}/${libdir}/libhello.so.1
    ln -s libhello.so.1 ${D}/${libdir}/libhello.so

    # Install header file
    install -d ${D}/${includedir}
    install -m 0644 ${S}/hello-lib.h ${D}/${includedir}

    # Install pkg-config metadata
    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${S}/hello-lib.pc ${D}${libdir}/pkgconfig/
}

# Package the shared library and development files
FILES:${PN} += "${libdir}/libhello.so.1.0 ${libdir}/libhello.so.1"
FILES:${PN}-dev += "${libdir}/libhello.so ${includedir}/hello-lib.h ${libdir}/pkgconfig"
