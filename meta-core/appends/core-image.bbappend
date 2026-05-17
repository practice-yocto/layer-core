# Add additional features to the generated image.
# In this case, enable the OpenSSH server so the target system
# boots with an SSH daemon running and ready for remote access.
EXTRA_IMAGE_FEATURES += "ssh-server-openssh"

# Post-processing function that writes the current value of
# ROOTFS_POSTPROCESS_COMMAND into a file inside the target rootfs.
# This is mainly for debugging to verify which postprocess commands
# were active during image creation.
test_postprocess_func() {
    echo "${ROOTFS_POSTPROCESS_COMMAND}" > ${IMAGE_ROOTFS}/yocto.txt
}

# Register the function so it runs during the rootfs post-processing stage.
ROOTFS_POSTPROCESS_COMMAND += "test_postprocess_func;"


# Create a dummy directory inside the final root filesystem.
# This demonstrates how custom post-processing steps can modify
# the generated rootfs after all packages have been installed.
create_dummy_dir() {
    mkdir ${IMAGE_ROOTFS}/dummy
}

# Register the directory creation step as another post-processing command.
ROOTFS_POSTPROCESS_COMMAND += "create_dummy_dir;"
