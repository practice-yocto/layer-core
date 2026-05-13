DESCRIPTION = "Core Linux Kernel"
SECTION = "kernel"
LICENSE = "GPLv2"

inherit kernel
inherit kernel-yocto

# branch, name, tag and nocheckout are passed to git fetcher
SRC_URI = "git://github.com/practice-yocto/linux.git;protocol=https;branch=main"
SRCREV = "${AUTOREV}"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

# The defconfig file is used to configure the kernel build.
KBUILD_DEFCONFIG = "defconfig"

LINUX_VERSION ?= "6.6"
LINUX_VERSION_EXTENSION ?= "-core"

# SRCPV: ${GIT_COMMIT} truncated to 7 characters
PROVIDES += "virtual/kernel"
PV = "${LINUX_VERSION}+git${SRCPV}"
COMPATIBLE_MACHINE = "core"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
